package model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String fullname;
    private String username;
    private String email;
    private String password;
    private String salt;
    private int admin;
    private String status;

    public User(String email, String storedPassword, String salt, int admin) {
        this.email = email;
        this.password = storedPassword;
        this.salt = salt;
        this.admin = admin;
        this.username = email;
        this.fullname = email; // default; can be set later
        this.status = "active";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return fullname;
    }

    public String getUserType() {
        switch (admin) {
            case 0:
                return "Customer";
            case 1:
                return "Chief Executive Officer";
            case 2:
                return "Kitchen Manager";
            case 3:
                return "Branch Manager";
            case 4:
                return "Franchise Owner";
            case 5:
                return "Marketing Officer";
            case 6:
                return "Financial Officer";
            case 7:
                return "Food Delivery Partners";
            default:
                return "Customer";
        }
    }

}