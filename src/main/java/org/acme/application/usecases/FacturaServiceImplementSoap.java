package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.gbm.adapter.JsonProcessingException_Exception;
import org.acme.application.interfaces.input.IFacturaServiceSoap;
import org.acme.application.interfaces.output.IProductSoap;
import org.acme.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImplementSoap implements IFacturaServiceSoap {

    @Inject
    IProductSoap iProducts;

    @Override
    public Factura createFactura (int limit, int skip) throws JsonProcessingException_Exception {
        return new Factura(iProducts.obtenerProducts(limit, skip));
    }

}
