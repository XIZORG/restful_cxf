package com.westbrain.sandbox.jaxrs.provider;

import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorContainer;
import com.westbrain.sandbox.jaxrs.exception.model.FieldErrorModel;
import com.westbrain.sandbox.jaxrs.exception.provider.BadRequestExceptionMapper;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BadRequestExceptionMapperTest {
    @Test
    void testToResponse() {
        String text = "some exception text";
        BadRequestException exception = new BadRequestException(text);
        BadRequestExceptionMapper mapper = new BadRequestExceptionMapper();
        Response response = mapper.toResponse(exception);
        FieldErrorContainer fieldErrorContainer = (FieldErrorContainer) response.getEntity();
        List<FieldErrorModel> fieldErrorModels = fieldErrorContainer.getErrorList();
        FieldErrorModel fieldErrorModel = fieldErrorModels.get(0);

        assertNotNull(response);
        assertNotNull(response.getEntity());

        assertEquals(fieldErrorModel.getErrorMessage(), text);
    }
}
