package controller.fooddeliverypartners;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FoodDeliveryPartnersController implements Initializable {

    @FXML
    public Button btnCheckMarkets;

    @FXML
    public Button btnIncomingOrders;

    @FXML
    public Button btnEarnings;

    @FXML
    public Button btnCustomerSupport;

    @FXML
    public Button btnPerformanceReports;

    @FXML
    public Button btnLogOut;

    @FXML
    public StackPane dashContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void btnCheckMarketsOnClick() {
        loadPage("checkmarket.fxml");
    }

    @FXML
    public void btnIncomingOrdersOnClick() {
        loadPage("incomingorders.fxml");
    }

    @FXML
    public void btnEarningsOnClick() {
        loadPage("earnings.fxml");
    }

    @FXML
    public void btnCustomerSupportOnClick() {
        loadPage("customersupport.fxml");
    }

    @FXML
    public void btnPerformanceReportsOnClick() {
        loadPage("performancereports.fxml");
    }

    @FXML
    public void btnLogOutOnClick() {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    public void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            dashContent.getChildren().clear();
            dashContent.getChildren().add(root);
        } catch (IOException e) {            
        }
    }
}