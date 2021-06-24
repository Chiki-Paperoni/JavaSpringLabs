package com.example.demo.model;

import domain.order.Order;
import domain.user.DUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaLabsDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    private static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Order> getUserOrders(String name) {
        List<Order> userOrders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            PreparedStatement prepare = connection.prepareStatement("SELECT * FROM orders WHERE username=?");
            prepare.setString(1,name);
            ResultSet result = prepare.executeQuery();
            while(result.next()) {
                Order o = new Order();
                o.setUsername(result.getString("username"));
                o.setAmount(result.getInt("amount"));
                o.setType(result.getString("type"));
                o.setMaterial(result.getString("material"));
                userOrders.add(o);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userOrders;
    }
    public void setUserOrder(String name,Order o) {
        try {
            PreparedStatement prepare = connection.prepareStatement("INSERT INTO orders VALUES(11,?,?,?,?)");
            prepare.setString(1,name);
            prepare.setString(2,o.getType());
            prepare.setDouble(3,o.getAmount());
            prepare.setString(4,o.getMaterial());
            prepare.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
