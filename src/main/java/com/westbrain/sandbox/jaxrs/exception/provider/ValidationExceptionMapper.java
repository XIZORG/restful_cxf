package com.westbrain.sandbox.jaxrs.exception.provider;

import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        FieldErrorContainer errorInfoContainer = new FieldErrorContainer();
        errorInfoContainer.getErrorList().add(new FieldErrorModel("chek if the request is correct"));
        return Response
                .status(HttpStatus.BAD_REQUEST.value())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorInfoContainer)
                .build();
    }
}
