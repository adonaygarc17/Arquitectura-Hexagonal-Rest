package org.acme.application.interfaces.output;

import org.acme.domain.entities.Product;

import java.util.List;

public interface IProducts {
    public List<Product> obtenerProducts(int skip,int limit);
}
