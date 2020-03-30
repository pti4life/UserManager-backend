package com.innovitech.usermanager.microservice.exception;

import com.innovitech.usermanager.api.exception.CustomException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException e) {
        return Response.status(e.getHttpStatusCode()).entity(e.getMessage()).build();
    }

}
