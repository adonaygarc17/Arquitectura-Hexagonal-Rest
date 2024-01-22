package org.acme.infraestructure.output.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.interfaces.output.IProductService;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.input.rest.dtos.ProductDTO;
import org.acme.infraestructure.input.rest.dtos.ResponseDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductsImplement  {

    @RestClient
    IProductService productService;


    public List<Product> obtenerProducts( int limit , int skip){
        ResponseDTO products = productService.getProducts( limit , skip);

        ArrayList<Product> ProductsList = new ArrayList<>();
        for (ProductDTO p: products.getProducts()){
            Product product = new Product(
                    p.getId(),
                    p.getTitle(),
                    p.getCategory(),
                    p.getThumbnail(),
                    p.getDiscountPercentage(),
                    p.getStock(),
                    p.getBrand(),
                    p.getPrice()
            );
            ProductsList.add(product);
        }
        return ProductsList;

    }
}
