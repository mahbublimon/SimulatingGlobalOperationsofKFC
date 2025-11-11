package controller.franchiseowner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FranchiseOwnerController {

    @FXML
    public void btnMarketAnalysisOnClick(ActionEvent event) {
        showInfoAlert("Market Analysis page is coming soon.");
    }

    @FXML
    public void btnBranchPerformanceEvaluationOnClick(ActionEvent event) {
        showInfoAlert("Branch Performance Evaluation page is coming soon.");
    }

    @FXML
    public void btnMenuAndPricingManagementOnClick(ActionEvent event) {
        showInfoAlert("Menu & Pricing Management page is coming soon.");
    }

    @FXML
    public void btnInventoryManagementOnClick(ActionEvent event) {
        showInfoAlert("Inventory Management page is coming soon.");
    }

    @FXML
    public void btnFranchiseCommunicationOnClick(ActionEvent event) {
        showInfoAlert("Franchise Communication page is coming soon.");
    }

    @FXML
    public void btnQualityAssuranceOnClick(ActionEvent event) {
        showInfoAlert("Quality Assurance page is coming soon.");
    }

    @FXML
    public void btnFinancialAnalysisReportingOnClick(ActionEvent event) {
        showInfoAlert("Financial Analysis & Reporting page is coming soon.");
    }

    @FXML
    public void btnMarketingOnClick(ActionEvent event) {
        showInfoAlert("Marketing page is coming soon.");
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
