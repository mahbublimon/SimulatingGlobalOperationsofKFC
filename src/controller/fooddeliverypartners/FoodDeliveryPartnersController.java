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
        showInfoAlert("Check Markets page is coming soon.");
    }

    @FXML
    public void btnIncomingOrdersOnClick() {
        showInfoAlert("Incoming Orders page is coming soon.");
    }

    @FXML
    public void btnEarningsOnClick() {
        showInfoAlert("Earnings page is coming soon.");
    }

    @FXML
    public void btnCustomerSupportOnClick() {
        showInfoAlert("Customer Support page is coming soon.");
    }

    @FXML
    public void btnPerformanceReportsOnClick() {
        showInfoAlert("Performance Reports page is coming soon.");
    }

    @FXML
    public void btnLogOutOnClick() {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    private void showInfoAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Coming Soon");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}