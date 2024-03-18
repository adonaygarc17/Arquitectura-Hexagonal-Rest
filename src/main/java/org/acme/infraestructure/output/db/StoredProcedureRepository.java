package org.acme.infraestructure.output.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.acme.infraestructure.output.db.dto.FacturaDb;
import org.acme.infraestructure.output.db.dto.FacturaDbDTO;
import org.acme.infraestructure.output.db.dto.ProductDb;
import org.acme.infraestructure.output.db.dto.ProductDbDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class StoredProcedureRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ArrayList<FacturaDbDTO> getAllFactura(){
        StoredProcedureQuery facturaQuery = entityManager.createStoredProcedureQuery("GetAllBills");
        List<Object[]>facturaRows = facturaQuery.getResultList();

        StoredProcedureQuery productsQuery = entityManager.createStoredProcedureQuery("GetAllProducts");
        List<Object[]> productsRows = productsQuery.getResultList();

        ArrayList<FacturaDbDTO> dbBillDTOS = new ArrayList<>(facturaRows.size());
        for (Object[] b : facturaRows) {
            FacturaDbDTO billDto = new FacturaDbDTO(
                    (Number) b[1],
                    (Number) b[4],
                    (Number) b[2],
                    (Number) b[3],
                    mappingDbProducts(productsRows).stream().filter(dbProductsDTO -> dbProductsDTO.dbBillId().equals(b[0])).toList()
            );
            dbBillDTOS.add(billDto);
        }
        return dbBillDTOS;
    }
    private List<ProductDbDTO> mappingDbProducts(List<Object[]> productsRows) {
        List<ProductDbDTO> dbProductsDTOAList = new ArrayList<>();
        for (Object[] p: productsRows) {
            ProductDbDTO dbProductsDTO = new ProductDbDTO(
                    (String) p[8],
                    (String) p[7],
                    (String) p[2],
                    (String) p[1],
                    (String) p[6],
                    (Number) p[3],
                    (Number) p[4],
                    (Number) p[5]

            );
            dbProductsDTOAList.add(dbProductsDTO);
        }
        return dbProductsDTOAList;
    }
}
