package org.acme.infraestructure.input.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.IFacturaServiceRest;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.FacturaDTO;
import org.acme.infraestructure.input.rest.dtos.FacturaProductDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class RestApiTest {
    @InjectMock
    IFacturaServiceRest facturaServiceMock;

    @Inject
    RestApi restApi;

    @Test
    void shouldGetFacturaWhenUseSkipLimit(){

        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",899.0));

        when(facturaServiceMock.createFactura(anyInt(),anyInt())).thenReturn(new Factura(productosSimulados));

        Response response = restApi.getFactura(1,1);

        assertEquals(200,response.getStatus());

        verify(facturaServiceMock,times(1)).createFactura(1,1);
    }

    @Test
    void shouldCreateFacturaWhenRecivedProductList() {
        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",899.0));

        Factura facturaSimulada = new Factura(productosSimulados);

        FacturaDTO facturaDTO = restApi.createFactura(facturaSimulada);

        List<FacturaProductDTO> facturaProductEsperados = Arrays.asList(new FacturaProductDTO(2, "iPhonex", "smartphones",899.0,"Apple", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",
                17,152.83,843.17));

        FacturaDTO facturaDTOEsperada = new FacturaDTO(facturaProductEsperados ,746.17,152.83,97,843.17);

        assertEquals(facturaDTOEsperada.getFacturaSubtotal(),facturaDTO.getFacturaSubtotal());
        assertEquals(facturaDTOEsperada.getTotal(),facturaDTO.getTotal());
    }
 }



