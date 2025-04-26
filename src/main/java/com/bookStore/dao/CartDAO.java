package com.bookStore.dao;

import com.bookStore.entity.CartItem;
import com.bookStore.exception.CartNotFoundException;
import com.bookStore.exception.InvalidInputException;

import java.util.*;

public class CartDAO {
    private static CartDAO instance;
    private final Map<String, List<CartItem>> carts = new HashMap<>();

    private CartDAO() {
    }

    public static synchronized CartDAO getInstance() {
        if (instance == null)
            instance = new CartDAO();
        return instance;
    }

    public List<CartItem> getCart(String customerId) {
        return carts.computeIfAbsent(customerId, k -> new ArrayList<>());
    }

    public void addItem(String customerId, CartItem item) {
        if (item.getBookId() == null || item.getBookId().isBlank())
            throw new InvalidInputException("Book ID required");
        getCart(customerId).add(item);
    }

    public void updateItem(String customerId, String bookId, int qty) {
        List<CartItem> cart = getCart(customerId);
        for (CartItem it : cart) {
            if (it.getBookId().equals(bookId)) {
                it.setQuantity(qty);
                return;
            }
        }
        throw new CartNotFoundException("Item not in cart: " + bookId);
    }

    public void removeItem(String customerId, String bookId) {
        List<CartItem> cart = getCart(customerId);
        boolean removed = cart.removeIf(it -> it.getBookId().equals(bookId));
        if (!removed)
            throw new CartNotFoundException("Item not in cart: " + bookId);
    }

    public void clearCart(String customerId) {
        carts.remove(customerId);
    }
}
