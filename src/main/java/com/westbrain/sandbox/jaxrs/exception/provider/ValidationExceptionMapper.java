package com.westbrain.sandbox.jaxrs.exception.provider;

import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Log4j2
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.warn(e.getMessage());
        FieldErrorContainer errorInfoContainer = new FieldErrorContainer();
        errorInfoContainer.getErrorList().add(new FieldErrorModel("chek if the request is correct"));
        return Response
                .status(HttpStatus.BAD_REQUEST.value())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorInfoContainer)
                .build();
    }
}
