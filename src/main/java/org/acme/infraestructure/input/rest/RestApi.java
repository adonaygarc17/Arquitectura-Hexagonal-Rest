package org.acme.infraestructure.input.rest;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.infraestructure.output.DB.DTO.FacturaDb;
import org.acme.infraestructure.output.DB.DTO.ProductDb;
import org.acme.infraestructure.output.DB.FacturaRepository;
import org.acme.infraestructure.output.DB.StoredProcedureRepository;
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
@Transactional
public class RestApi {

  @Inject
  IFacturaService facturaService;

  @Inject
  StoredProcedureRepository storedProcedureRepository;

  @Inject
  FacturaRepository facturaRepository;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFactura( @NotNull @QueryParam("limit") int limit, @NotNull @QueryParam("skip") int skip){
    return Response.ok(this.createFactura(facturaService.createFactura(limit,skip))).build();

  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllFacturas(){
    return Response.status(200).entity(storedProcedureRepository.getAllFactura()).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public PanacheEntityBase getFacturaById(@QueryParam("id") UUID id){
    return FacturaDb.findById(id);
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response updateProducts(@QueryParam("id") UUID id,@QueryParam("limit") int limit, @QueryParam("skip")int skip){
    FacturaDb updateFactura = FacturaDb.findById(id);

    if(updateFactura == null){
      throw new NotFoundException();
    }
    ProductDb.delete("facturaDb",updateFactura);
    Factura newProduct = facturaService.generarFactura(limit, skip);
    updateFactura.setId(id);
    updateFactura.setFacturaSubtotal(newProduct.getFacturaSubTotal());
    updateFactura.setFacturaTax(newProduct.getFacturaTax());
    updateFactura.setTotalDiscount(newProduct.getTotalDiscount());
    updateFactura.setTotal(newProduct.getTotal());
    updateFactura.setProducts(mappingProducts(newProduct.getProducts(),updateFactura));
    updateFactura.persist();
    return Response.status(200).entity(updateFactura ).build();

  }
  public List<ProductDb> mappingProducts(List<Product> products, FacturaDb factura){
    ArrayList<ProductDb> dbProducts = new ArrayList<>();
    for (Product p : products) {
      ProductDb dbProduct = new ProductDb();
      dbProduct.setTitle(p.getTitle());
      dbProduct.setCategory(p.getCategory());
      dbProduct.setBrand(p.getBrand());
      dbProduct.setThumbnail(p.getThumbnail());
      dbProduct.setDiscountPercentage(p.getDiscountPercentage());
      dbProduct.setDiscountTotal(p.getDiscountTotal());
      dbProduct.setFinalPrice(p.getFinalPrice());
      dbProduct.setFacturaDb(factura);
      dbProducts.add(dbProduct);
    }
    return  dbProducts;
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

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void deleteFactura(@PathParam("id") UUID id){
    facturaRepository.deleteById(id);
 }

  @GET
  @Path("{value}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<FacturaDb> getFacturaByTotal(@QueryParam("total") double total, @QueryParam("discount") double totalDiscount){
    if(total >0 || totalDiscount >0){
      return facturaRepository.list("total =?1 OR totalDiscount = ?2",total, totalDiscount);
    }else {
      throw new NotFoundException("La factura no fue encontrada");
    }

 }



}
