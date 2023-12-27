package org.acme.utils.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.acme.utils.exceptions.dtos.ErrorResponse;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e ){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setId("400 bad request");
        errorResponse.setDescription(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();

    }
}
