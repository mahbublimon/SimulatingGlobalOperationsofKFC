package controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpkg.utils.HelperMethods;
import mainpkg.utils.PasswordUtils;
import model.Datasource;
import model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

    private Stage dialogStage;
    private final UserSessionController userSessionController = UserSessionController.getInstance();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String gender = genderComboBoxField.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty() || gender == null) {
            HelperMethods.alertBox("Please fill in all fields.", Alert.AlertType.ERROR, "Registration Failed!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            HelperMethods.alertBox("Passwords do not match.", Alert.AlertType.ERROR, "Registration Failed!");
            return;
        }

        if (datePicker.getValue() == null) {
            HelperMethods.alertBox("Please select a valid date of birth.", Alert.AlertType.ERROR, "Registration Failed!");
            return;
        }

        String dateOfBirth = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Task<Boolean> addUserTask = new Task<>() {
            @Override
            protected Boolean call() {
                String salt = PasswordUtils.getSalt(30);
                String securePassword = PasswordUtils.generateSecurePassword(password, salt);
                return Datasource.getInstance().insertNewUser(firstName, lastName, email, phone, securePassword, salt, gender, dateOfBirth);
            }
        };

        addUserTask.setOnSucceeded(e -> {
            if (addUserTask.getValue()) {
                User user = getUserByEmail(email);
                if (user != null) {
                    setUserSession(user);
                    closeCurrentWindow(actionEvent);
                    redirectToDashboard(user.getAdmin());
                    saveUserToFile(user);
                } else {
                    HelperMethods.alertBox("Error fetching user data after registration.", Alert.AlertType.ERROR, "Registration Failed!");
                }
            } else {
                HelperMethods.alertBox("Error registering user.", Alert.AlertType.ERROR, "Registration Failed!");
            }
        });

        addUserTask.setOnFailed(e -> {
            Throwable exception = e.getSource().getException();
            HelperMethods.alertBox("Error during user registration: " + exception.getMessage(), Alert.AlertType.ERROR, "Registration Failed!");
        });

        new Thread(addUserTask).start();
    }

    private void closeCurrentWindow(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
    }

    private User getUserByEmail(String email) {
        return Datasource.getInstance().getUserByEmail(email);
    }

    private void setUserSession(User user) {
        userSessionController.setUserId(user.getId());
        userSessionController.setUserFullName(user.getFullName());
        userSessionController.setUserName(user.getEmail());
        userSessionController.setUserAdmin(user.getAdmin());
    }

    public void redirectToDashboard(int adminStatus) {
        userSessionController.setUserAdmin(adminStatus);
    }

    private void saveUserToFile(User user) {
        String filePath = "userdata.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(user);
            HelperMethods.logInfo("User data saved to file.");
        } catch (IOException e) {
            HelperMethods.logError("Error saving user data to file: " + e.getMessage());
        }
    }
}