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
        loadPage("MarketAnalysis.fxml");
    }

    @FXML
    public void btnProductOnClick(ActionEvent event) {
        loadPage("Product.fxml");
    }

    @FXML
    public void btnBranchDataOnClick(ActionEvent event) {
        loadPage("BranchData.fxml");
    }

    @FXML
    public void btnPromotionsOnClick(ActionEvent event) {
        loadPage("Promotions.fxml");
    }

    @FXML
    public void btnLoyaltyProgrammeOnClick(ActionEvent event) {
        loadPage("LoyaltyProgramme.fxml");
    }

    @FXML
    public void btnFeedbackOnClick(ActionEvent event) {
        loadPage("Feedback.fxml");
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
    }

    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/fxml/" + fxmlFileName));
            Parent root = loader.load();
            dashContent.getChildren().clear();
            dashContent.getChildren().add(root);
        } catch (IOException e) {            
        }
    }
}

