package org.acme.utils.exceptions;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.utils.exceptions.dtos.ErrorResponse;

import java.net.SocketTimeoutException;

@Provider
public class SocketTimeOutExceptionMapper implements ExceptionMapper <SocketTimeoutException> {
    @Override
    public Response toResponse(SocketTimeoutException e ){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId("504 gateway timeout");
        errorResponse.setDescription("Se ha generado un timeout al obtener los productos");
        return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(errorResponse).build();

    }
}
