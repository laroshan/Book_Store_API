package com.bookStore.exception;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());

        int status;
        switch (e.getClass().getSimpleName()) {
            case "BookNotFoundException":
            case "AuthorNotFoundException":
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
        return Response.status(status).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}
