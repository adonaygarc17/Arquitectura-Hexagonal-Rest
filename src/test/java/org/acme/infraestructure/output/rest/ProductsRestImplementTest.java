package org.acme.infraestructure.output.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.application.interfaces.output.IProductService;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.ProductDTO;
import org.acme.infraestructure.input.rest.dtos.ResponseDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@QuarkusTest
class ProductsRestImplementTest {

   /* @RestClient
    @InjectMock
    IProductService productService;

    @Test
    void obtenerProducto(){
        Mockito.when(productService.getProducts(anyInt(),anyInt()))
                .thenReturn(listaSimulada());

        assertEquals(productosSimulados, productService.getProducts(1,1).getProducts());
    }

    public ResponseDTO listaSimulada(){
        ProductDTO product = new ProductDTO(
                2,
                "iPhonex",
                "smartphones",
                "https://i.dummyjson.com/data/products/2/thumbnail.jpg",
                17,
                34,
                "Apple",
                899.0
        );
        return product;
    }
    List<Product> productosSimulados = Arrays.asList(new Product(2, "iPhonex", "smartphones", "https://i.dummyjson.com/data/products/2/thumbnail.jpg",17,34,
            "Apple",899.0));*/
}