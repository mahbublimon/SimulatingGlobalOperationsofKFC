package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository {
    private static final String FILE_PATH = "orders.dat";

    public static synchronized List<Order> getAll() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                //noinspection unchecked
                return (List<Order>) obj;
            }
        } catch (Exception ignored) {
        }
        return new ArrayList<>();
    }

    public static synchronized List<Order> getByUserId(int userId) {
        return getAll().stream().filter(o -> o.getUser_id() == userId).collect(Collectors.toList());
    }

    public static synchronized void saveAll(List<Order> orders) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(orders);
        } catch (IOException ignored) {
        }
    }

    public static synchronized void add(Order order) {
        List<Order> orders = getAll();
        orders.add(order);
        saveAll(orders);
    }

    public static synchronized boolean removeById(int id) {
        List<Order> orders = getAll();
        boolean removed = orders.removeIf(o -> o.getId() == id);
        if (removed) saveAll(orders);
        return removed;
    }

    public static synchronized boolean update(Order updated) {
        List<Order> orders = getAll();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == updated.getId()) {
                orders.set(i, updated);
                saveAll(orders);
                return true;
            }
        }
        return false;
    }
}