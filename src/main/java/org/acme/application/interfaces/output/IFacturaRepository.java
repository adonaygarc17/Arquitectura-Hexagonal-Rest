package org.acme.application.interfaces.output;

import org.acme.domain.entities.Factura;

public interface IFacturaRepository {

    void saveFactura(Factura factura);

}
