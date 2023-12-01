package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpkg.utils.HelperMethods;
import mainpkg.utils.PasswordUtils;
import model.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String providedPassword = passwordField.getText();

        if (email.isEmpty() || providedPassword.isEmpty()) {
            HelperMethods.alertBox("Please enter the Email and Password.", null, "Login Failed!", Alert.AlertType.ERROR);
        } else {
            User user = getUserFromDataFile(email);
            if (user == null) {
                HelperMethods.alertBox("There is no user registered with that email!", null, "Login Failed!", Alert.AlertType.ERROR);
            } else {
                boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, user.getPassword(), user.getSalt());

                if (passwordMatch) {
                    redirectDashboard(event, user.getAdmin());
                } else {
                    HelperMethods.alertBox("Incorrect Email or Password", null, "Login Failed!", Alert.AlertType.ERROR);
                }
            }
        }
    }

    private User getUserFromDataFile(String email) {
        // Fetch user details from file or database based on email
        return null; // Replace this with actual implementation
    }

    private void redirectDashboard(ActionEvent event, int admin) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader;
            if (admin == 0) {
                loader = new FXMLLoader(getClass().getResource("/view/user/dashboard.fxml"));
            } else if (admin == 1) {
                loader = new FXMLLoader(getClass().getResource("/view/admin/dashboard.fxml"));
            } else {
                // Handle other admin statuses or provide a default redirection
                return;
            }

            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            HelperMethods.logError("Error loading dashboard: " + e.getMessage());
        }
    }

    @FXML
    public void handleRegisterButtonAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/register.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
