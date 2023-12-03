package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;


public class KFCSimulationController extends LoginController {

    protected void redirectDashboard(ActionEvent event, int admin) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader loader;
        switch (admin) {
            case 0 -> loader = new FXMLLoader(getClass().getResource("/view/customer/customerdashboard.fxml"));
            case 1 -> loader = new FXMLLoader(getClass().getResource("/view/admin/customerdashboard.fxml"));
            default -> {
                return;
            }
        }
        setDashboardScene(loader, stage);
    }

    public void setDashboardScene(FXMLLoader loader, Stage stage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}