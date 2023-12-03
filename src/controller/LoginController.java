package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpkg.utils.HelperMethods;
import mainpkg.utils.PasswordUtils;
import model.Datasource;
import model.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> userSelectionComboBox;

    @FXML
    public void initialize() {
        userSelectionComboBox.getItems().addAll(
                "Customer",
                "Admin",  // You can customize the user types as needed
                "Kitchen Manager",
                "Branch Manager",
                "Franchise Owner",
                "Marketing Officer",
                "Financial Officer",
                "CEO"
        );
    }

    @FXML
    public void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String providedPassword = passwordField.getText();
        String selectedUserType = userSelectionComboBox.getValue();

        if (email.isEmpty() || providedPassword.isEmpty() || selectedUserType == null) {
            HelperMethods.alertBox("Please enter the Email, Password, and select User Type.", null, "Login Failed!", Alert.AlertType.ERROR);
        } else {
            User user = getUserFromDataFile(email);

            if (user == null || !user.getUserType().equals(selectedUserType)) {
                HelperMethods.alertBox("There is no user registered with that email or selected user type!", null, "Login Failed!", Alert.AlertType.ERROR);
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
        return Datasource.getInstance().getUserByEmail(email);
    }

    private void redirectDashboard(ActionEvent event, int admin) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader;
            switch (admin) {
                case 0 -> loader = new FXMLLoader(getClass().getResource("/view/customerdashboard.fxml"));
                case 1 -> loader = new FXMLLoader(getClass().getResource("/view/cheifexecutiveofficerdashboard.fxml"));
                case 2 -> loader = new FXMLLoader(getClass().getResource("/view/kitchenmanagerdashboard.fxml"));
                case 3 -> loader = new FXMLLoader(getClass().getResource("/view/branchmanagerdashboard.fxml"));
                case 4 -> loader = new FXMLLoader(getClass().getResource("/view/franchiseownerdashboard.fxml"));
                case 5 -> loader = new FXMLLoader(getClass().getResource("/view/marketingofficerdashboard.fxml"));
                case 6 -> loader = new FXMLLoader(getClass().getResource("/view/financialofficerdashboard.fxml"));
                case 7 -> loader = new FXMLLoader(getClass().getResource("/view/fooddeliverypartners.fxml"));
                default -> {
                    return;
                }
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