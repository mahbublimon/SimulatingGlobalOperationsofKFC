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
        loadPage("/view/customer/orders.fxml", "Orders Page");
    }

    @FXML
    public void btnInformationOnClick(ActionEvent event) {
        loadPage("/view/customer/information.fxml", "Information Page");
    }

    @FXML
    public void btnFeedbackOpinionOnClick(ActionEvent event) {
        loadPage("/view/customer/feedbackopinion.fxml", "Feedback & Opinion Page");
    }

    @FXML
    public void btnChangePasswordOnClick(ActionEvent event) {
        loadPage("/view/customer/changepassword.fxml", "Change Password Page");
    }

    @FXML
    public void btnSignOutOnClick(ActionEvent event) {
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
}