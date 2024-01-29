package org.acme.infraestructure.output.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
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
        StoredProcedureQuery facturaQuery = entityManager.createStoredProcedureQuery("GetAllBills")
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        List<Object[]>facturaRows = facturaQuery.getResultList();

        StoredProcedureQuery productsQuery = entityManager.createStoredProcedureQuery("GetAllProducts")
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR);
        List<Object[]> productsRows = productsQuery.getResultList();

        ArrayList<FacturaDbDTO> dbBillDTOS = new ArrayList<>(facturaRows.size());
        for (Object[] b : facturaRows) {
            FacturaDbDTO billDto = new FacturaDbDTO(
                    (double) b[0],
                    (double) b[1],
                    (double) b[2],
                    (double) b[3],
                    mappingDbProducts(productsRows).stream().filter(dbProductsDTO -> dbProductsDTO.dbBillId().equals(b[4])).toList()
            );
            dbBillDTOS.add(billDto);
        }
        return dbBillDTOS;
    }
    private List<ProductDbDTO> mappingDbProducts(List<Object[]> productsRows) {
        List<ProductDbDTO> dbProductsDTOAList = new ArrayList<>();
        for (Object[] p: productsRows) {
            ProductDbDTO dbProductsDTO = new ProductDbDTO(
                    (String) p[0],
                    (String) p[1],
                    (String) p[2],
                    (String) p[3],
                    (String) p[4],
                    (double) p[5],
                    (double) p[6],
                    (double) p[7]

            );
            dbProductsDTOAList.add(dbProductsDTO);
        }
        return dbProductsDTOAList;
    }
}
