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
        // Add logic to load the Menu page
        loadPage("Menu.fxml");
    }

    @FXML
    public void btnInventoryOnClick(ActionEvent event) {
        loadPage("Inventory.fxml");
    }

    @FXML
    public void btnCustomerFeedbackOnClick(ActionEvent event) {
        loadPage("CustomerFeedback.fxml");
    }

    @FXML
    public void btnDelivaryPartnerOnClick(ActionEvent event) {
        loadPage("DeliveryPartners.fxml");
    }

    @FXML
    public void btnEmployeeScheduleOnClick(ActionEvent event) {
        loadPage("EmployeeSchedule.fxml");
    }

    @FXML
    public void btnEmployeeMaintenanceOnClick(ActionEvent event) {
        loadPage("EmployeeMaintenance.fxml");
    }

    @FXML
    public void btnFinancialReportOnClick(ActionEvent event) {
        loadPage("FinancialReport.fxml");
    }

    @FXML
    public void btnHealthSafetyProtocolOnClick(ActionEvent event) {
        loadPage("HealthSafetyProtocol.fxml");
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        Stage stage = (Stage) btnHome3.getScene().getWindow();
        stage.close();
    }

    private void loadPage(String fxmlFileName) {
        // Load a new FXML page into the dashContent StackPane
        try {
            StackPane newPage = FXMLLoader.load(getClass().getResource(fxmlFileName));
            dashContent.getChildren().setAll(newPage);
        } catch (IOException e) {
            
        }
    }
}

