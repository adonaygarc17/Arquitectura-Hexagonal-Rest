package org.acme.infraestructure.input.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.FacturaDTO;
import org.acme.infraestructure.input.rest.dtos.FacturaProductDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class RestApiTest {

    @Test
    void getDefinedProductsTest(){
        IFacturaService facturaServiceMock = mock(IFacturaService.class);

        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",899.0));

        when(facturaServiceMock.createFactura(anyInt(),anyInt())).thenReturn(new Factura(productosSimulados));

        RestApi restApi = new RestApi(facturaServiceMock);

        Response response = restApi.getDefinedProducts(1,1);

        assertEquals(200,response.getStatus());

        verify(facturaServiceMock,times(1)).createFactura(1,1);
    }

    @Test
    void createFactura() {
        IFacturaService iFacturaService = mock(IFacturaService.class);
        RestApi restApi = new RestApi( iFacturaService);

        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",899.0));

        Factura facturaSimulada = new Factura(productosSimulados);

        FacturaDTO facturaDTO = restApi.createFactura(facturaSimulada);

        List<FacturaProductDTO> facturaProductEsperados = Arrays.asList(new FacturaProductDTO(2, "iPhonex", "smartphones",899.0,"Apple", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",
                17,152.83,843.17));

        FacturaDTO facturaDTOEsperada = new FacturaDTO(facturaProductEsperados ,746.17,152.83,97,843.17);

        assertEquals(facturaDTOEsperada.getFacturaSubtotal(),facturaDTO.getFacturaSubtotal());
    }


    @Test
    void getDefinedProducts() {
        given().when().post("/factura?skip=1&limit=1")
                .then().statusCode(200);
    }
    @Test
    void getDefinedProductsErrorLimit() {
        given().when().post("/factura?skip=1&limit=112")
                .then().statusCode(400);
    }
    @Test
    void getDefinedProductsSkip() {
        given().when().post("/factura?skip=123&limit=12")
                .then().statusCode(400);
    }

    @Test
    void testError500 (){
        IFacturaService facturaServiceMock = mock(IFacturaService.class);

        when(facturaServiceMock.createFactura(anyInt(),anyInt())).thenThrow(new RuntimeException("Internal Server Error"));
        RestApi restApi = new RestApi(facturaServiceMock);
        try{
            Response response = restApi.getDefinedProducts(0,10);
            fail("Ninguna Excepcion fue lanzada ");
        } catch (RuntimeException e){
            assertEquals("Internal Server Error", e.getMessage());
        }
        verify(facturaServiceMock, times(1)).createFactura(0,10);
    }


}