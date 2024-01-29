package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.application.interfaces.input.IDeleteFacturaById;
import org.acme.infraestructure.output.db.FacturaRepository;
import org.acme.infraestructure.output.db.dto.FacturaDb;

import java.util.UUID;

@ApplicationScoped
public class IDeleteFacturaByIdImplement implements IDeleteFacturaById {
    @Inject
    FacturaRepository facturaRepository;
    @Override
    public String deleteFacturaById(UUID id) {
        FacturaDb facturaDb = facturaRepository.findById(id);
        if(facturaDb !=null){
            facturaRepository.deleteById(id);
            return "La factura: " + id +" ha sido eliminada";
        }else {
           return "La factura no fue encontrrada ";
        }
    }
}
