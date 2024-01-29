package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.application.interfaces.output.IFacturaRepository;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Factura;

@ApplicationScoped
public class FacturaServiceImplement implements IFacturaService {

   @Inject
   IProducts iProducts;

   @Inject
   IFacturaRepository facturaRepository;


    @Override
    public Factura createFactura (int limit, int skip){
        Factura factura = new Factura(iProducts.obtenerProducts(limit, skip));
        facturaRepository.saveFactura(factura);
        return factura;
    }

    @Override
    public Factura generarFactura(int limit, int skip) {
        Factura factura = new Factura(iProducts.obtenerProducts(limit, skip));
        return factura;
    }


}
