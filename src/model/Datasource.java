package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Datasource {
    private static final Datasource instance = new Datasource();

    private Datasource() {
    }

    public static Datasource getInstance() {
        return instance;
    }

    public List<Product> getAllProducts(int sortOrder) {
        // Not implemented in this iteration
        return null;
    }

    public User getUserByEmail(String email) {
        File file = new File("userdata.dat");
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof User) {
                User user = (User) obj;
                if (email.equalsIgnoreCase(user.getEmail())) {
                    return user;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public Boolean insertNewUser(String firstName, String lastName, String email, String phone, String securePassword, String salt, String gender, String dateOfBirth) {
        try {
            User user = new User(email, securePassword, salt, 0); // default to Customer
            user.setFullname(firstName + " " + lastName);
            user.setUsername(email);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userdata.dat"))) {
                oos.writeObject(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
