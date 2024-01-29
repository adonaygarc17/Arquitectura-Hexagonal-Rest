package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.application.interfaces.input.ISearchFacturaById;
import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.UUID;

@ApplicationScoped
public class ISearchFacturaByIdImplement implements ISearchFacturaById {
    @Override
    public FacturaDb searchFacturaById(UUID id) {
        return FacturaDb.findById(id);
    }
}
