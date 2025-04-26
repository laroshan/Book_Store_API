package com.bookStore.exception;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import java.util.*;

@Provider
public class GlobalExceptionMapper
        implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException ex) {
        Map<String, String> err = new HashMap<>();
        err.put("error", ex.getClass().getSimpleName());
        err.put("message", ex.getMessage());

        int status;
        switch (ex.getClass().getSimpleName()) {
            case "BookNotFoundException":
            case "AuthorNotFoundException":
                status = 404;
                break;
            case "CustomerNotFoundException":
            case "CartNotFoundException":
                status = 404;
                break;
            case "InvalidInputException":
            case "OutOfStockException":
                status = 400;
                break;
            default:
                status = 500;
                break;
        }
        return Response.status(status)
                .entity(err)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
