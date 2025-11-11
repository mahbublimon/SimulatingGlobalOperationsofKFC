package controller.marketingofficer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MarketingOfficerController {

    @FXML
    public StackPane dashContent;

    @FXML
    public void btnMarketAnalysisOnClick(ActionEvent event) {
        showInfoAlert("Market Analysis page is coming soon.");
    }

    @FXML
    public void btnProductOnClick(ActionEvent event) {
        showInfoAlert("Product page is coming soon.");
    }

    @FXML
    public void btnBranchDataOnClick(ActionEvent event) {
        showInfoAlert("Branch Data page is coming soon.");
    }

    @FXML
    public void btnPromotionsOnClick(ActionEvent event) {
        showInfoAlert("Promotions page is coming soon.");
    }

    @FXML
    public void btnLoyaltyProgrammeOnClick(ActionEvent event) {
        showInfoAlert("Loyalty Programme page is coming soon.");
    }

    @FXML
    public void btnFeedbackOnClick(ActionEvent event) {
        showInfoAlert("Feedback page is coming soon.");
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
    }

    private void showInfoAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

