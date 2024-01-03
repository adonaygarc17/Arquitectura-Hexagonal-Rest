package org.acme.utils.exceptions;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@QuarkusTest
class ThrowableMapperTest {
    @Test
    void shouldUseThrowableMapperWhenTriggerThrowable (){
        ThrowableMapper mapper = new ThrowableMapper();

        // Simular un Throwable
        Throwable e = mock(Throwable.class);

        // Invocar el método toResponse
        Response response = mapper.toResponse(e);

        // Verificar el estado de la respuesta
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}