package org.acme.application.interfaces.output;

import org.acme.domain.entities.Product;

import java.util.List;

public interface IProductsRest {
     List<Product> obtenerProducts(int skip,int limit);
}
