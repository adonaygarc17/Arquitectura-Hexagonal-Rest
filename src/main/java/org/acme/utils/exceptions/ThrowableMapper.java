package org.acme.utils.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.utils.exceptions.dtos.ErrorResponse;

import java.util.UUID;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e ){
        String errorId = UUID.randomUUID().toString();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId(errorId);
        errorResponse.setDescription(e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();

    }


}
