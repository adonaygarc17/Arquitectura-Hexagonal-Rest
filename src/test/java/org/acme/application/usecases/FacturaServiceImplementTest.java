package org.acme.application.usecases;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;


import static io.restassured.RestAssured.given;

@QuarkusTest
class FacturaServiceImplementTest {


    @Test
    void createFactura() {
        IProducts iProductsMock = mock(IProducts.class);
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",549.0));
        when(iProductsMock.obtenerProducts(1, 1)).thenReturn(productosSimulados);

        FacturaServiceImplement facturaServiceImplement = new FacturaServiceImplement(iProductsMock);

        Factura factura = facturaServiceImplement.createFactura(1,1);

        verify(iProductsMock, times(1)).obtenerProducts(1,1);

        assertEquals(productosSimulados,factura.getProducts());
    }
}