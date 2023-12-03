package mainpkg.utils;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert.AlertType;

public class HelperMethods {

    public static boolean validateFullName(String fullName) {
        Matcher matcher = Pattern.compile("^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$", Pattern.CASE_INSENSITIVE).matcher(fullName);
        return matcher.find();
    }

    public static boolean validateUsername(String username) {
        Matcher matcher = Pattern.compile("^[A-Za-z]\\w{4,29}$", Pattern.CASE_INSENSITIVE).matcher(username);
        return matcher.find();
    }

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        return password.matches("^.{6,16}$");
    }

    public static boolean validateProductQuantity(String integer) {
        return integer.matches("-?(0|[1-9]\\d*)");
    }

    public static boolean validateProductPrice(String productPrice) {
        return productPrice.matches("^[0-9]+(|\\.)[0-9]+$");
    }

    public static void alertBox(String infoMessage, AlertType alertType, String title, AlertType ERROR) {
        Alert alert = new Alert(alertType);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    public static void logError(String message) {
        System.err.println("[ERROR] " + message);
    }

    public static void alertBox(String please_fill_in_all_fields, AlertType alertType, String registration_Failed) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}