package com.bookStore.dao;

import com.bookStore.entity.Customer;
import com.bookStore.exception.CustomerNotFoundException;
import com.bookStore.exception.InvalidInputException;

import java.util.*;

public class CustomerDAO {
    private static CustomerDAO instance;
    private final Map<String, Customer> store = new HashMap<>();

    private CustomerDAO() {
    }

    public static synchronized CustomerDAO getInstance() {
        if (instance == null)
            instance = new CustomerDAO();
        return instance;
    }

    public void add(Customer c) {
        if (c.getId() == null || c.getId().isBlank())
            throw new InvalidInputException("Customer ID required");
        if (store.containsKey(c.getId()))
            throw new InvalidInputException("Duplicate Customer ID: " + c.getId());
        store.put(c.getId(), c);
    }

    public Customer get(String id) {
        Customer c = store.get(id);
        if (c == null)
            throw new CustomerNotFoundException("Customer not found: " + id);
        return c;
    }

    public List<Customer> getAll() {
        return new ArrayList<>(store.values());
    }

    public void update(String id, Customer c) {
        if (!store.containsKey(id))
            throw new CustomerNotFoundException("Customer not found: " + id);
        store.put(id, c);
    }

    public void delete(String id) {
        if (!store.containsKey(id))
            throw new CustomerNotFoundException("Customer not found: " + id);
        store.remove(id);
    }
}
