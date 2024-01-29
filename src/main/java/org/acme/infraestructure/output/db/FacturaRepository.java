package org.acme.infraestructure.output.db;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.UUID;


@ApplicationScoped
public class FacturaRepository implements PanacheRepositoryBase<FacturaDb,UUID> {


}
