package com.bookStore.dao;

import com.bookStore.entity.Author;
import com.bookStore.exception.AuthorNotFoundException;
import com.bookStore.exception.InvalidInputException;

import java.util.*;

public class AuthorDAO {
    private static AuthorDAO instance;
    private final Map<String, Author> store = new HashMap<>();

    private AuthorDAO() {
    }

    public static synchronized AuthorDAO getInstance() {
        if (instance == null)
            instance = new AuthorDAO();
        return instance;
    }

    public void add(Author a) {
        if (a.getId() == null || a.getId().isBlank())
            throw new InvalidInputException("Author ID required");
        if (store.containsKey(a.getId()))
            throw new InvalidInputException("Duplicate Author ID: " + a.getId());
        store.put(a.getId(), a);
    }

    public Author get(String id) {
        Author a = store.get(id);
        if (a == null)
            throw new AuthorNotFoundException("Author not found: " + id);
        return a;
    }

    public List<Author> getAll() {
        return new ArrayList<>(store.values());
    }

    public void update(String id, Author a) {
        if (!store.containsKey(id))
            throw new AuthorNotFoundException("Author not found: " + id);
        store.put(id, a);
    }

    public void delete(String id) {
        if (!store.containsKey(id))
            throw new AuthorNotFoundException("Author not found: " + id);
        store.remove(id);
    }
}
