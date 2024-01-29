package org.acme.application.interfaces.input;

import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.List;

public interface ISearchFacturaByValue {

    List<FacturaDb> getFacturaByValue(double total, double totalDiscount);
}
