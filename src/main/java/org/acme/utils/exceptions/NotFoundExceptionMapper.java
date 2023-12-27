package org.acme.utils.exceptions;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.utils.exceptions.dtos.ErrorResponse;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e ){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId("400 bad request");
        errorResponse.setDescription(e.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();

    }
}
