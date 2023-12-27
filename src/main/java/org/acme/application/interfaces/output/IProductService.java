package org.acme.application.interfaces.output;

import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.acme.infraestructure.input.rest.dtos.ResponseDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@Path("/products")
public interface IProductService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ResponseDTO getProducts(@NotNull @QueryParam("skip") int skip, @NotNull @QueryParam("limit") int limit);
}
