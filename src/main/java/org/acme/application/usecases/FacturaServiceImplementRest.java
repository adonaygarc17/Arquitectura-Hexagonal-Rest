package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaServiceRest;
import org.acme.application.interfaces.output.IProductsRest;
import org.acme.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImplementRest implements IFacturaServiceRest {

   @Inject
   IProductsRest iProductsRest;


    @Override
    public Factura createFactura (int limit, int skip){
        return new Factura(iProductsRest.obtenerProducts(limit, skip));
    }
}
