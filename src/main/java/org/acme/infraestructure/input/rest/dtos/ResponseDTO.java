package org.acme.infraestructure.input.rest.dtos;

import java.util.List;

public class ResponseDTO {
    List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }


}
