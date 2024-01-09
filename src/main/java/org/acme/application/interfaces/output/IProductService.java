package org.acme.application.interfaces.output;

import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.acme.infraestructure.input.rest.dtos.ResponseDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@Path("/products")
public interface IProductService {

    @GET
    ResponseDTO getProducts( @NotNull @QueryParam("limit") int limit ,@NotNull @QueryParam("skip") int skip);
}
