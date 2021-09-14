package com.westbrain.sandbox.jaxrs.exception.provider;

import com.fasterxml.jackson.core.JsonParseException;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Log4j2
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    @Override
    public Response toResponse(JsonParseException e) {
        log.warn(e.getMessage());
        FieldErrorContainer errorInfoContainer = new FieldErrorContainer();
        errorInfoContainer.getErrorList().add(new FieldErrorModel("chek json in your request"));
        return Response
                .status(HttpStatus.BAD_REQUEST.value())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorInfoContainer)
                .build();
    }
}