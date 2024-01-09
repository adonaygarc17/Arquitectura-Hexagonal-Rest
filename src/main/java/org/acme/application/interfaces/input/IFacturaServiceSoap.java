package org.acme.application.interfaces.input;

import net.gbm.adapter.JsonProcessingException_Exception;
import org.acme.application.interfaces.validator.ProductRangeValidate;
import org.acme.domain.entities.Factura;

public interface IFacturaServiceSoap {
    @ProductRangeValidate
    Factura createFactura( int limit, int skip) throws JsonProcessingException_Exception;
}
