package model;

import java.sql.*;
import java.util.List;

public class Datasource {
    private static final String DB_NAME = "store_manager.sqlite";
    private static final String CONNECTION_STRING = "jdbc:sqlite:C:/src/app/db/" + DB_NAME;

    private static final Datasource instance = new Datasource();

    private Connection conn;

    private Datasource() {
    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }

    public List<Product> getAllProducts(int sortOrder) {
        // Your implementation here
        return null;
    }
    public User getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean insertNewUser(String firstName, String lastName, String email, String phone, String securePassword, String salt, String gender, String dateOfBirth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
