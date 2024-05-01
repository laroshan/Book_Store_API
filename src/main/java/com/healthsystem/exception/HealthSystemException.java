package com.healthsystem.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
class HealthSystemExceptionMapper implements ExceptionMapper<HealthSystemException> {
    @Override
    public Response toResponse(HealthSystemException exception) {
        return Response.status(exception.getHttpStatus())
                .entity(exception.getMessage())
                .build();
    }
}

public class HealthSystemException extends RuntimeException {
    private final Response.Status httpStatus;

    public HealthSystemException(String message, Response.Status httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Response.Status getHttpStatus() {
        return httpStatus;
    }
}
