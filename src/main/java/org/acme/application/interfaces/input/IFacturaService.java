package org.acme.application.interfaces.input;


import org.acme.application.interfaces.validator.ProductRangeValidate;
import org.acme.domain.entities.Factura;

public interface IFacturaService {

   @ProductRangeValidate
   Factura createFactura(int skip, int limit);
}
