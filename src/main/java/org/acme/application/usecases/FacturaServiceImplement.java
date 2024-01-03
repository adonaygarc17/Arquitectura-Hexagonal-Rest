package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImplement implements IFacturaService {


   @Inject
   IProducts iProducts;


    @Override
    public Factura createFactura (int skip, int limit){
        return new Factura(iProducts.obtenerProducts(skip, limit));
    }
}
