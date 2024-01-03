package org.acme.utils.exceptions;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.acme.utils.exceptions.dtos.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.net.SocketTimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


@QuarkusTest
class SocketTimeOutExceptionMapperTest {

    @Test
    void getDefinedProductsErrorTimeOut() {
        SocketTimeOutExceptionMapper mapper = new SocketTimeOutExceptionMapper();

        // Simular una SocketTimeoutException
        SocketTimeoutException e = mock(SocketTimeoutException.class);

        // Invocar el método toResponse
        Response response = mapper.toResponse(e);

        // Verificar el estado de la respuesta
        assertEquals(Response.Status.GATEWAY_TIMEOUT.getStatusCode(), response.getStatus());

        // Verificar el cuerpo de la respuesta, si es necesario
        ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
        assertEquals("504 gateway timeout", errorResponse.getId());
        assertEquals("Se ha generado un timeout al obtener los productos", errorResponse.getDescription());

    }
}