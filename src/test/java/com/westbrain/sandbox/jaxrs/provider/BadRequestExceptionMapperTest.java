package com.westbrain.sandbox.jaxrs.provider;

import com.westbrain.sandbox.jaxrs.exception.provider.BadRequestExceptionMapper;
import org.junit.jupiter.api.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BadRequestExceptionMapperTest {
    @Test
    void testToResponse() {
        String text = "some exception text";
        BadRequestException exception = new BadRequestException(text);
        BadRequestExceptionMapper mapper = new BadRequestExceptionMapper();
        Response response = mapper.toResponse(exception);
        assertNotNull(response);
        assertNotNull(response.getEntity());
    }
}
