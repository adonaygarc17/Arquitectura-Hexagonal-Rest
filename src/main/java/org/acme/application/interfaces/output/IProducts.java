package org.acme.application.interfaces.output;

import org.acme.domain.entities.Product;

import java.util.List;

public interface IProducts {
     List<Product> obtenerProducts(int limit, int skip);
}
