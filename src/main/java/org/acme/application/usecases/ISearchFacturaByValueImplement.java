package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.input.ISearchFacturaByValue;
import org.acme.infraestructure.output.db.FacturaRepository;
import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.List;

@ApplicationScoped
public class ISearchFacturaByValueImplement implements ISearchFacturaByValue {
    @Inject
    FacturaRepository facturaRepository;
    @Override
    public List<FacturaDb> getFacturaByValue(double total, double totalDiscount) {
        return facturaRepository.list("total =?1 OR totalDiscount = ?2",total, totalDiscount);
    }
}
