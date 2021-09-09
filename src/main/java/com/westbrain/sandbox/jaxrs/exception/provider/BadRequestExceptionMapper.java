package com.westbrain.sandbox.jaxrs.exception.provider;

import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import org.springframework.http.HttpStatus;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    @Override
    public Response toResponse(BadRequestException e) {
        FieldErrorContainer errorInfoContainer = new FieldErrorContainer();
        errorInfoContainer.getErrorList().add(new FieldErrorModel(e.getMessage()));
        return Response
                .status(HttpStatus.BAD_REQUEST.value())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorInfoContainer)
                .build();
    }
}


