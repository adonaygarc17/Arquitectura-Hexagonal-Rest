package org.acme.infraestructure.input.rest;

import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.IFacturaServiceRest;
import org.acme.utils.exceptions.dtos.ErrorResponse;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.FacturaDTO;
import org.acme.infraestructure.input.rest.dtos.FacturaProductDTO;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;


@APIResponse(
        description = "Muestra factura con los descuentos, impuesto y el total",
        responseCode = "200",
        content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = FacturaDTO.class))
)


@APIResponse
        (responseCode ="400", description = "Bad Request",
                content = @Content(mediaType ="application/json",
                        schema =@Schema(implementation = ErrorResponse.class)
                ))
@APIResponse
        (responseCode ="504", description = "Gateway Timeout",
                content = @Content(mediaType ="application/json",
                        schema =@Schema(implementation = ErrorResponse.class)
                ))

@APIResponse
        (responseCode ="500", description = "Internal Server Error",
                content = @Content(mediaType ="application/json",
                        schema =@Schema(implementation = ErrorResponse.class)
                ))


@Path("/factura")
public class RestApi {

@Inject
IFacturaServiceRest facturaService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFactura( @NotNull @QueryParam("limit") int limit, @NotNull @QueryParam("skip") int skip){

    return Response.ok(this.createFactura(facturaService.createFactura(limit,skip))).build();

  }

  public FacturaDTO createFactura(Factura factura) {
    ArrayList<FacturaProductDTO> productsDTO = new ArrayList<>();
    for (Product p: factura.getProducts()) {
      FacturaProductDTO dtoProduct = new FacturaProductDTO(
              p.getId(),
              p.getTitle(),
              p.getCategory(),
              p.getPrice(),
              p.getBrand(),
              p.getThumbnail(),
              p.getDiscountPercentage(),
              p.getDiscountTotal(),
              p.getFinalPrice()
      );
      productsDTO.add(dtoProduct);
    }
    return new FacturaDTO(
            productsDTO,
            factura.getFacturaSubTotal(),
            factura.getTotalDiscount(),
            factura.getFacturaTax(),
            factura.getTotal());
  }

}
