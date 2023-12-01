package controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mainpkg.utils.HelperMethods;
import mainpkg.utils.PasswordUtils;
import model.Datasource;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

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
        String dateOfBirth = datePicker.getValue() != null ? datePicker.getValue().format(DateTimeFormatter.ISO_DATE) : null;

        if (!validateInput(firstName, lastName, email, phone, password, confirmPassword, gender, dateOfBirth)) {
            return;
        }

        if (accountExists(email)) {
            HelperMethods.alertBox("An account with this email already exists. Please log in.", Alert.AlertType.WARNING, "Account Exists", Alert.AlertType.ERROR);
            return;
        }

        if (!password.equals(confirmPassword)) {
            HelperMethods.alertBox("Passwords do not match. Please enter the same password in both fields.", Alert.AlertType.ERROR, "Registration Failed!", Alert.AlertType.ERROR);
            return;
        }

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
                User user = getUserByEmail(email);
                setUserSession(user);
                saveUserToFile(user);

                Node node = (Node) actionEvent.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();

                redirectToDashboard(user.getAdmin());
            }
        });

        addUserTask.setOnFailed(e -> {
            Throwable exception = e.getSource().getException();
            HelperMethods.alertBox("Error during user registration: " + exception.getMessage(), Alert.AlertType.ERROR, "Registration Failed!", Alert.AlertType.ERROR);
        });

        new Thread(addUserTask).start();
    }

    private boolean validateInput(String firstName, String lastName, String email, String phone, String password,
                                   String confirmPassword, String gender, String dateOfBirth) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() || gender == null || dateOfBirth == null) {
            HelperMethods.alertBox("Please fill in all fields.", Alert.AlertType.ERROR, "Registration Failed!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
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
        String filePath = "F:\\KFC\\SimulatingGlobalOperationsofKFC\\src\\data\\userdata.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(user);
            HelperMethods.logInfo("User data saved to file.");
        } catch (IOException e) {
            HelperMethods.logError("Error saving user data to file: " + e.getMessage());
        }
    }
}
