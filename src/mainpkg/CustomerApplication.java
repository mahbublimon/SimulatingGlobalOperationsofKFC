package mainpkg;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("KFC Global Operations");
        primaryStage.setScene(new Scene(root));
        // Controller wiring not needed for login initial display
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

