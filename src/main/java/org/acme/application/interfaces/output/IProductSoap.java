package org.acme.application.interfaces.output;


import net.gbm.adapter.JsonProcessingException_Exception;
import org.acme.domain.entities.Product;

import java.util.List;

public interface IProductSoap {

    List<Product> obtenerProducts(int skip, int limit) throws JsonProcessingException_Exception;

}
