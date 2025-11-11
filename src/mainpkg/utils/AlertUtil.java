package mainpkg.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertUtil {

    private AlertUtil() {}

    public static void info(String title, String content) {
        show(AlertType.INFORMATION, title, content);
    }

    public static void warn(String title, String content) {
        show(AlertType.WARNING, title, content);
    }

    public static void error(String title, String content) {
        show(AlertType.ERROR, title, content);
    }

    private static void show(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}