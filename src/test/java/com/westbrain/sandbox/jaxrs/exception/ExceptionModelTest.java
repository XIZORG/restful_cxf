package com.westbrain.sandbox.jaxrs.exception;

import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionModelTest {
    @Test
    void testErrorModel() {
        String text = "some error text";
        BadRequestException badRequestException = new BadRequestException(text);
        FieldErrorModel fieldErrorModel = new FieldErrorModel(badRequestException.getMessage());
        assertEquals(fieldErrorModel.getErrorMessage(), badRequestException.getMessage());
    }

    @Test
    void testErrorModelContainer() {
        String text = "some error text";
        BadRequestException badRequestException = new BadRequestException(text);
        FieldErrorModel fieldErrorModel = new FieldErrorModel(badRequestException.getMessage());
        FieldErrorContainer fieldErrorContainer = new FieldErrorContainer();
        fieldErrorContainer.setErrorList(List.of(fieldErrorModel));

        assertEquals(fieldErrorContainer.getErrorList().get(0), fieldErrorModel);
    }
}
