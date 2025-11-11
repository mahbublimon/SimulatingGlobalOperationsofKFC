package controller.customer.pages;

import controller.UserSessionController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainpkg.utils.AlertUtil;
import model.Order;
import model.OrderRepository;

import java.time.LocalDate;

public class PlaceOrderController {

    @FXML
    private TextField productField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;

    @FXML
    private void initialize() {
        if (statusCombo != null) {
            statusCombo.getItems().setAll("Processing", "Delivered", "Cancelled");
            statusCombo.getSelectionModel().selectFirst();
        }
        // Prefill user email/address when available
        UserSessionController usc = UserSessionController.getInstance();
        if (usc.getUserEmail() != null) {
            emailField.setText(usc.getUserEmail());
        }
        // Leave address empty for user input
    }

    @FXML
    private void onCancel(javafx.event.ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    private void onSubmit(javafx.event.ActionEvent event) {
        String product = productField.getText();
        String priceStr = priceField.getText();
        String status = statusCombo.getValue();
        String address = addressField.getText();
        String email = emailField.getText();

        if (product == null || product.isBlank() || priceStr == null || priceStr.isBlank() ||
                status == null || address == null || address.isBlank() || email == null || email.isBlank()) {
            AlertUtil.warn("Validation", "Please fill all fields.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException nfe) {
            AlertUtil.error("Validation", "Price must be a number.");
            return;
        }

        UserSessionController usc = UserSessionController.getInstance();
        Order o = new Order();
        o.setId((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
        o.setProduct_id(0);
        o.setProduct_name(product);
        o.setUser_id(usc.getUserId());
        o.setUser_full_name(usc.getUserFullName());
        o.setShipping_address(address);
        o.setOrder_email(email);
        o.setOrder_date(LocalDate.now().toString());
        o.setOrder_status(status);
        o.setOrder_price(price);

        OrderRepository.add(o);
        AlertUtil.info("Success", "Order placed successfully.");
        closeWindow(event);
    }

    private void closeWindow(javafx.event.ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}