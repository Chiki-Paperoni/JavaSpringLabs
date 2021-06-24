package com.example.demo.model;

import domain.order.Order;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Service
@NoArgsConstructor
@Component
public class OrderService {
    private OrderDao orders = new OrderDao();
    static int count = 0;

    public List<Order> getUserOrders(String name) {
        return orders.getUserOrders(name);
    }
    public void setUserOrder(String name,Order o) {
        orders.setUserOrder(name,o);
    }
}
