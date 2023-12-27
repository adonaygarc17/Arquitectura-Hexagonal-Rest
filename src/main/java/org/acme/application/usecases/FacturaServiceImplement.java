package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImplement implements IFacturaService {


    private final IProducts iProducts;

    @Inject
    public FacturaServiceImplement(IProducts iProducts){
        this.iProducts = iProducts;
    }


    @Override
    public Factura createFactura (int skip, int limit){
        return new Factura(iProducts.obtenerProducts(skip, limit));
    }
}
