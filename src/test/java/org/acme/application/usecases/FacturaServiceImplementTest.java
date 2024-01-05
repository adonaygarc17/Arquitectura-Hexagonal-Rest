package org.acme.application.usecases;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaServiceRest;
import org.acme.application.interfaces.output.IProductsRest;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;



@QuarkusTest
class FacturaServiceImplementTest {

    @InjectMock
    IProductsRest iProductsRestMock;

    @Inject
    IFacturaServiceRest iFacturaServiceRest;

    @Test
    void createFactura() {

        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",549.0));

        Mockito.when(iProductsRestMock.obtenerProducts(anyInt(),anyInt())).thenReturn(productosSimulados);

        Factura factura = iFacturaServiceRest.createFactura(1,1);

        verify(iProductsRestMock, times(1)).obtenerProducts(1,1);

        assertEquals(productosSimulados,factura.getProducts());
    }
}