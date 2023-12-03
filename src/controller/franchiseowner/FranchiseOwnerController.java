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
        loadPage("MarketAnalysis.fxml", "Market Analysis");
    }

    @FXML
    public void btnBranchPerformanceEvaluationOnClick(ActionEvent event) {
        loadPage("BranchPerformanceEvaluation.fxml", "Branch Performance Evaluation");
    }

    @FXML
    public void btnMenuAndPricingManagementOnClick(ActionEvent event) {
        loadPage("MenuAndPricingManagement.fxml", "Menu and Pricing Management");
    }

    @FXML
    public void btnInventoryManagementOnClick(ActionEvent event) {
        loadPage("InventoryManagement.fxml", "Inventory Management");
    }

    @FXML
    public void btnFranchiseCommunicationOnClick(ActionEvent event) {
        loadPage("FranchiseCommunication.fxml", "Franchise Communication");
    }

    @FXML
    public void btnQualityAssuranceOnClick(ActionEvent event) {
        loadPage("QualityAssurance.fxml", "Quality Assurance");
    }

    @FXML
    public void btnFinancialAnalysisReportingOnClick(ActionEvent event) {
        loadPage("FinancialAnalysisReporting.fxml", "Financial Analysis Reporting");
    }

    @FXML
    public void btnMarketingOnClick(ActionEvent event) {
        loadPage("Marketing.fxml", "Marketing");
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
    }

    private void loadPage(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/fxml/" + fxmlFileName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {            
        }
    }
}
