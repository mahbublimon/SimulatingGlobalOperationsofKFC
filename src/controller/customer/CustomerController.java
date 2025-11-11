package controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {

    @FXML
    public void btnOrder(ActionEvent event) {
        loadPage("/view/customer/pages/orders/orders.fxml", "Orders Page");
    }

    @FXML
    public void btnInformationOnClick(ActionEvent event) {
        showInfoAlert("Information page is coming soon.");
    }

    @FXML
    public void btnFeedbackOpinionOnClick(ActionEvent event) {
        showInfoAlert("Feedback & Opinion page is coming soon.");
    }

    @FXML
    public void btnChangePasswordOnClick(ActionEvent event) {
        showInfoAlert("Change Password page is coming soon.");
    }

    @FXML
    public void btnSignOutOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
            // Close current window
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            showInfoAlert("Unable to sign out. Please try again.");
        }
    }

    private void loadPage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
        }
    }

    private void showInfoAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}