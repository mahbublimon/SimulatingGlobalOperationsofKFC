package controller.customer.pages;

import controller.UserSessionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import mainpkg.utils.AlertUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;
import model.OrderRepository;
import model.Order;

public class UserOrdersController {

    @FXML
    private BorderPane ordersContainer;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> statusCombo;

    private TableView<Order> tableView;
    private ObservableList<Order> masterData;
    private FilteredList<Order> filteredData;
    private SortedList<Order> sortedData;
    private ContextMenu tableContextMenu;

    @FXML
    private void initialize() {
        setupTable();
        setupFilters();
        bindRowDoubleClick();
        setupContextMenu();
    }

    private void setupTable() {
        tableView = new TableView<>();

        TableColumn<Order, Integer> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Order, String> productCol = new TableColumn<>("Product");
        productCol.setCellValueFactory(new PropertyValueFactory<>("product_name"));

        TableColumn<Order, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("order_date"));

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("order_status"));

        TableColumn<Order, Double> priceCol = new TableColumn<>("Price ($)");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("order_price"));

        tableView.getColumns().addAll(idCol, productCol, dateCol, statusCol, priceCol);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        int currentUserId = UserSessionController.getInstance().getUserId();
        // Prefer persisted orders; fallback to sample if none
        var persisted = OrderRepository.getByUserId(currentUserId);
        if (persisted != null && !persisted.isEmpty()) {
            masterData = FXCollections.observableArrayList(persisted);
        } else {
            masterData = FXCollections.observableArrayList(getSampleOrdersForUser(currentUserId));
        }
        filteredData = new FilteredList<>(masterData, p -> true);
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);
        ordersContainer.setCenter(tableView);
    }

    private void setupFilters() {
        if (statusCombo != null) {
            statusCombo.getItems().setAll("All", "Delivered", "Processing", "Cancelled");
            statusCombo.getSelectionModel().selectFirst();
        }

        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldV, newV) -> applyFilters());
        }

        if (statusCombo != null) {
            statusCombo.valueProperty().addListener((obs, oldV, newV) -> applyFilters());
        }
    }

    private void applyFilters() {
        final String query = searchField != null ? searchField.getText() : "";
        final String status = statusCombo != null ? statusCombo.getValue() : "All";

        filteredData.setPredicate(order -> {
            boolean matchesText = true;
            boolean matchesStatus = true;

            if (query != null && !query.isBlank()) {
                String q = query.toLowerCase();
                matchesText = (order.getProduct_name() != null && order.getProduct_name().toLowerCase().contains(q))
                        || (order.getOrder_email() != null && order.getOrder_email().toLowerCase().contains(q))
                        || (order.getShipping_address() != null && order.getShipping_address().toLowerCase().contains(q));
            }

            if (status != null && !status.equals("All")) {
                matchesStatus = status.equalsIgnoreCase(order.getOrder_status());
            }

            return matchesText && matchesStatus;
        });
    }

    private void bindRowDoubleClick() {
        tableView.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(evt -> {
                if (evt.getClickCount() == 2 && !row.isEmpty()) {
                    Order o = row.getItem();
                    StringBuilder sb = new StringBuilder();
                    sb.append("Order ID: ").append(o.getId()).append('\n');
                    sb.append("Product: ").append(o.getProduct_name()).append('\n');
                    sb.append("Price: $").append(String.format("%.2f", o.getOrder_price())).append('\n');
                    sb.append("Date: ").append(o.getOrder_date()).append('\n');
                    sb.append("Status: ").append(o.getOrder_status()).append('\n');
                    sb.append("Ship To: ").append(o.getShipping_address()).append('\n');
                    sb.append("Email: ").append(o.getOrder_email());
                    AlertUtil.info("Order Details", sb.toString());
                }
            });
            return row;
        });
    }

    private void setupContextMenu() {
        MenuItem editStatus = new MenuItem("Edit Status");
        editStatus.setOnAction(e -> {
            Order selected = tableView.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            ChoiceDialog<String> dialog = new ChoiceDialog<>(selected.getOrder_status(), "Processing", "Delivered", "Cancelled");
            dialog.setTitle("Edit Order Status");
            dialog.setHeaderText(null);
            dialog.setContentText("Choose new status:");
            dialog.initOwner(ordersContainer.getScene().getWindow());
            dialog.showAndWait().ifPresent(newStatus -> {
                selected.setOrder_status(newStatus);
                boolean ok = OrderRepository.update(selected);
                if (ok) {
                    refreshOrders();
                    AlertUtil.info("Updated", "Order status updated.");
                } else {
                    AlertUtil.error("Update Failed", "Could not update order.");
                }
            });
        });

        MenuItem deleteOrder = new MenuItem("Delete Order");
        deleteOrder.setOnAction(e -> {
            Order selected = tableView.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            if (!confirm("Delete Order", "Are you sure you want to delete order #" + selected.getId() + "?")) return;
            boolean removed = OrderRepository.removeById(selected.getId());
            if (removed) {
                refreshOrders();
                AlertUtil.info("Deleted", "Order deleted.");
            } else {
                AlertUtil.error("Delete Failed", "Could not delete order.");
            }
        });

        tableContextMenu = new ContextMenu(editStatus, deleteOrder);
        tableView.setContextMenu(tableContextMenu);
    }

    private boolean confirm(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> res = alert.showAndWait();
        return res.isPresent() && res.get() == ButtonType.OK;
    }

    @FXML
    private void btnPlaceOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer/pages/orders/placeorder.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Place Order");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            // Refresh after dialog closes
            refreshOrders();
        } catch (Exception e) {
            AlertUtil.error("Error", "Unable to open Place Order form.");
        }
    }

    private void refreshOrders() {
        int currentUserId = UserSessionController.getInstance().getUserId();
        masterData.setAll(OrderRepository.getByUserId(currentUserId));
        tableView.refresh();
    }

    private ObservableList<Order> getSampleOrdersForUser(int userId) {
        ObservableList<Order> orders = FXCollections.observableArrayList();

        Order o1 = new Order();
        o1.setId(1001);
        o1.setProduct_id(1);
        o1.setProduct_name("Zinger Burger Combo");
        o1.setUser_id(userId);
        o1.setUser_full_name(UserSessionController.getInstance().getUserFullName());
        o1.setShipping_address("123 Colonel St, Louisville");
        o1.setOrder_email(UserSessionController.getInstance().getUserEmail());
        o1.setOrder_date("2024-11-01");
        o1.setOrder_status("Delivered");
        o1.setOrder_price(9.99);
        orders.add(o1);

        Order o2 = new Order();
        o2.setId(1002);
        o2.setProduct_id(2);
        o2.setProduct_name("Bucket Meal");
        o2.setUser_id(userId);
        o2.setUser_full_name(UserSessionController.getInstance().getUserFullName());
        o2.setShipping_address("123 Colonel St, Louisville");
        o2.setOrder_email(UserSessionController.getInstance().getUserEmail());
        o2.setOrder_date("2024-11-05");
        o2.setOrder_status("Processing");
        o2.setOrder_price(19.49);
        orders.add(o2);

        Order o3 = new Order();
        o3.setId(1003);
        o3.setProduct_id(3);
        o3.setProduct_name("Chicken Popcorn");
        o3.setUser_id(userId);
        o3.setUser_full_name(UserSessionController.getInstance().getUserFullName());
        o3.setShipping_address("123 Colonel St, Louisville");
        o3.setOrder_email(UserSessionController.getInstance().getUserEmail());
        o3.setOrder_date("2024-11-09");
        o3.setOrder_status("Cancelled");
        o3.setOrder_price(5.49);
        orders.add(o3);

        return orders;
    }
}