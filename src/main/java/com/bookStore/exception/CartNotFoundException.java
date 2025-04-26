// CartNotFoundException.java
package com.bookStore.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String msg) {
        super(msg);
    }
}
