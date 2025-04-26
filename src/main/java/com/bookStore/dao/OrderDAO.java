package com.bookStore.dao;

import com.bookStore.entity.Order;
import com.bookStore.exception.InvalidInputException;

import java.util.*;

public class OrderDAO {
    private static OrderDAO instance;
    private final Map<String, List<Order>> orders = new HashMap<>();

    private OrderDAO() {
    }

    public static synchronized OrderDAO getInstance() {
        if (instance == null)
            instance = new OrderDAO();
        return instance;
    }

    public void addOrder(String customerId, Order o) {
        if (o.getId() == null || o.getId().isBlank())
            throw new InvalidInputException("Order ID required");
        orders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(o);
    }

    public List<Order> getOrders(String customerId) {
        return orders.getOrDefault(customerId, Collections.emptyList());
    }

    public Order getOrder(String customerId, String orderId) {
        return getOrders(customerId).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException("Order not found: " + orderId));
    }
}
