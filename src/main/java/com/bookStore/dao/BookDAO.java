package com.bookStore.dao;

import com.bookStore.entity.Book;
import com.bookStore.exception.BookNotFoundException;
import com.bookStore.exception.InvalidInputException;

import java.util.*;

public class BookDAO {
    private static BookDAO instance;
    private final Map<String, Book> store = new HashMap<>();

    private BookDAO() {
    }

    public static synchronized BookDAO getInstance() {
        if (instance == null)
            instance = new BookDAO();
        return instance;
    }

    public void add(Book b) {
        if (b.getId() == null || b.getId().isBlank())
            throw new InvalidInputException("Book ID must be provided");
        if (store.containsKey(b.getId()))
            throw new InvalidInputException("Duplicate Book ID: " + b.getId());
        store.put(b.getId(), b);
    }

    public Book get(String id) {
        Book b = store.get(id);
        if (b == null)
            throw new BookNotFoundException("Book not found: " + id);
        return b;
    }

    public List<Book> getAll() {
        return new ArrayList<>(store.values());
    }

    public void update(String id, Book b) {
        if (!store.containsKey(id))
            throw new BookNotFoundException("Book not found: " + id);
        store.put(id, b);
    }

    public void delete(String id) {
        if (!store.containsKey(id))
            throw new BookNotFoundException("Book not found: " + id);
        store.remove(id);
    }
}
