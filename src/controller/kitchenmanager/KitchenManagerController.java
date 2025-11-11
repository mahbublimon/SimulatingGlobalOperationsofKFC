package controller.kitchenmanager;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;

public class KitchenManagerController implements Initializable {

    @FXML
    public StackPane dashContent;

    @FXML
    public Button btnMenu;

    @FXML
    public Button btnInventory;

    @FXML
    public Button btnCustomerFeedback;

    @FXML
    public Button btnDelivaryPartner;

    @FXML
    public Button btnEmployeeSchedule;

    @FXML
    public Button btnEmployeeMaintenance;

    @FXML
    public Button btnFinancialReport;

    @FXML
    public Button btnHealthSafetyProtocol;

    @FXML
    public Button btnHome3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void btnMenuOnClick(ActionEvent event) {
        showInfoAlert("Menu page is coming soon.");
    }

    @FXML
    public void btnInventoryOnClick(ActionEvent event) {
        showInfoAlert("Inventory page is coming soon.");
    }

    @FXML
    public void btnCustomerFeedbackOnClick(ActionEvent event) {
        showInfoAlert("Customer Feedback page is coming soon.");
    }

    @FXML
    public void btnDelivaryPartnerOnClick(ActionEvent event) {
        showInfoAlert("Delivery Partners page is coming soon.");
    }

    @FXML
    public void btnEmployeeScheduleOnClick(ActionEvent event) {
        showInfoAlert("Employee Schedule page is coming soon.");
    }

    @FXML
    public void btnEmployeeMaintenanceOnClick(ActionEvent event) {
        showInfoAlert("Employee Maintenance page is coming soon.");
    }

    @FXML
    public void btnFinancialReportOnClick(ActionEvent event) {
        showInfoAlert("Financial Report page is coming soon.");
    }

    @FXML
    public void btnHealthSafetyProtocolOnClick(ActionEvent event) {
        showInfoAlert("Health & Safety Protocol page is coming soon.");
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        Stage stage = (Stage) btnHome3.getScene().getWindow();
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

