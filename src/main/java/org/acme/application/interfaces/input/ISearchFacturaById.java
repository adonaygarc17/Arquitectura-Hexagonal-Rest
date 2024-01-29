package org.acme.application.interfaces.input;

import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.UUID;

public interface ISearchFacturaById {

    FacturaDb searchFacturaById(UUID id);
}
