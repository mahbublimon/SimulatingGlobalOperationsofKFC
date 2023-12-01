package controller;

public class UserSessionController {

    private static int userId;
    private static String userFullName;
    private static String userName;
    private static String userEmail;
    private static String userStatus;
    private static int userAdmin;

    private static final UserSessionController instance = new UserSessionController();

    private UserSessionController() { }

    public static UserSessionController getInstance() {
        return instance;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public int getUserAdmin() {
        return userAdmin;
    }

    public void setUserFullName(String userFullName) {
        UserSessionController.userFullName = userFullName;
    }

    public void setUserName(String userName) {
        UserSessionController.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        UserSessionController.userEmail = userEmail;
    }

    public void setUserStatus(String userStatus) {
        UserSessionController.userStatus = userStatus;
    }

    public void setUserAdmin(int userAdmin) {
        UserSessionController.userAdmin = userAdmin;
    }

    public void setUserId(int userId) {
        UserSessionController.userId = userId;
    }

    public void cleanUserSession() {
        userId = 0;
        userFullName = null;
        userName = null;
        userEmail = null;
        userAdmin = 0;
        userStatus = null;
    }
}
