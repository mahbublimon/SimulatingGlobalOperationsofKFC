package controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainpkg.utils.HelperMethods;
import model.Datasource;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import mainpkg.utils.PasswordUtils;

public class RegisterController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> genderComboBoxField;

    private Stage dialogStage = new Stage();

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String gender = genderComboBoxField.getValue();
        String dateOfBirth = datePicker.getValue().toString(); // Change this to appropriate date handling

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() || gender == null || dateOfBirth.isEmpty()) {
            HelperMethods.alertBox("Please fill in all fields.", Alert.AlertType.ERROR, "Registration Failed!");
            return;
        }

        // Check if the account already exists
        if (accountExists(email)) {
            HelperMethods.alertBox("An account with this email already exists. Please log in.", Alert.AlertType.WARNING, "Account Exists");
            return;
        }

        // Password matching validation
        if (!password.equals(confirmPassword)) {
            HelperMethods.alertBox("Passwords do not match. Please enter the same password in both fields.", Alert.AlertType.ERROR, "Registration Failed!");
            return;
        }

        // Registration
        String salt = PasswordUtils.getSalt(30);
        String securePassword = PasswordUtils.generateSecurePassword(password, salt);

        Task<Boolean> addUserTask = new Task<Boolean>() {
            @Override
            protected Boolean call() {
                return Datasource.getInstance().insertNewUser(firstName, lastName, email, phone, securePassword, salt, gender, dateOfBirth);
            }
        };

        addUserTask.setOnSucceeded(e -> {
            if (addUserTask.valueProperty().get()) {
                // Retrieve the user after successful registration
                User user = getUserByEmail(email);

                // Set user session information
                setUserSession(user);

                // Close the current window
                Node node = (Node) actionEvent.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();

                // Redirect to the appropriate dashboard
                redirectToDashboard(user.getAdmin());

                // Save user data to file
                saveUserToFile(user);
            }
        });

        new Thread(addUserTask).start();
    }

    private boolean accountExists(String email) {
        try {
            return Datasource.getInstance().getUserByEmail(email) != null;
        } catch (SQLException e) {
            return false;
        }
    }

    private User getUserByEmail(String email) {
        try {
            return Datasource.getInstance().getUserByEmail(email);
        } catch (SQLException e) {
            return null;
        }
    }

    private void setUserSession(User user) {
        UserSessionController.setUserId(user.getId());
        UserSessionController.setUserFullName(user.getFullName());
        UserSessionController.setUserName(user.getEmail());
        // Set other session information as needed
    }

    private void redirectToDashboard(int adminStatus) {
        if (adminStatus == 0) {
            // Redirect to the user dashboard
        } else if (adminStatus == 1) {
            // Redirect to the admin dashboard
        }
    }

    private void saveUserToFile(User user) {
        // Update the file path as needed
        String filePath = "F:\\KFC\\SimulatingGlobalOperationsofKFC\\src\\data\\userdata.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(user);
            System.out.println("User data saved to file.");
        } catch (IOException e) {
        }
    }
}
