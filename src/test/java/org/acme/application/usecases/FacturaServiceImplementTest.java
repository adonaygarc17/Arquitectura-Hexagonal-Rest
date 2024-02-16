package org.acme.application.usecases;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.application.interfaces.output.IProducts;
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
    IProducts iProductsMock;

    @Inject
    IFacturaService iFacturaService;

    @Test
    void generateFactura() {

        //Crear listado de productos
        List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
                "Apple",549.0));

        Mockito.when(iProductsMock.obtenerProducts(anyInt(),anyInt())).thenReturn(productosSimulados);

        Factura factura = iFacturaService.generarFactura(1,1);

        verify(iProductsMock, times(1)).obtenerProducts(1,1);

        assertEquals(productosSimulados,factura.getProducts());
    }
}