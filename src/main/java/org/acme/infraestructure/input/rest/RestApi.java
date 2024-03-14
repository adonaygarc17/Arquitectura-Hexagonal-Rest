package org.acme.infraestructure.input.rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.*;
import org.acme.infraestructure.output.db.dto.FacturaDb;
import org.acme.infraestructure.output.db.StoredProcedureRepository;
import org.acme.infraestructure.output.db.dto.FacturaDbDTO;
import org.acme.utils.exceptions.dtos.ErrorResponse;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.FacturaDTO;
import org.acme.infraestructure.input.rest.dtos.FacturaProductDTO;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;




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
@Transactional
public class RestApi {

  @Inject
  IFacturaService facturaService;

  @Inject
  StoredProcedureRepository storedProcedureRepository;

  @Inject
  IDeleteFacturaById IDeleteFacturaById;

  @Inject
  ISearchFacturaByValue ISearchFacturaByValue;

  @Inject
  IUpdateFacturaDb IUpdateFacturaDb;

  @Inject
  ISearchFacturaById ISearchFacturaById;

  @Inject
  MeterRegistry registry;

  @APIResponse(
          description = "Crea y almacena la factura en la base de datos",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFactura( @NotNull @QueryParam("limit") int limit, @NotNull @QueryParam("skip") int skip){
    registry.counter("SaveFacturasCounter").increment();
    return Response.ok(this.createFactura(facturaService.createFactura(limit,skip))).build();

  }
  @APIResponse(
          description = "Muestra factura con los descuentos, impuesto y el total",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<FacturaDbDTO> getAllFacturas(){

    registry.counter("getFacturasCounter").increment();

    return storedProcedureRepository.getAllFactura();
  }

  @APIResponse(
          description = "Muestra factura con los descuentos, impuesto y el total",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFacturaById(@PathParam("id") UUID id){
    return Response.status(200).entity(ISearchFacturaById.searchFacturaById(id)).build();
  }
  @APIResponse(
          description = "Muestra factura con los descuentos, impuesto y el total",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @GET
  @Path("/search")
  @Produces(MediaType.APPLICATION_JSON)
  public List<FacturaDb> getFacturaByTotal(@PositiveOrZero @QueryParam("total") double total,@PositiveOrZero @QueryParam("discount") double totalDiscount){
      return ISearchFacturaByValue.getFacturaByValue(total,totalDiscount);

  }
  @APIResponse(
          description = "Actualiza la factura con nuevos productos",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response updateProducts(@PathParam("id") UUID id,@QueryParam("limit") int limit, @QueryParam("skip")int skip){
      return Response.status(200).entity(IUpdateFacturaDb.updateFactura(id,limit,skip)).build();
  }

  @APIResponse(
          description = "Elimina la factura segun el id",
          responseCode = "200",
          content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = FacturaDTO.class))
  )
  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteFactura(@PathParam("id") UUID id){

    return Response.status(200).entity(IDeleteFacturaById.deleteFacturaById(id)).build();
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
