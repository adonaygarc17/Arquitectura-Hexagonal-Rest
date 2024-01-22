package org.acme.infraestructure.output.DB;

import jakarta.ejb.ApplicationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.acme.infraestructure.output.DB.DTO.FacturaDb;
import org.acme.infraestructure.output.DB.DTO.FacturaDbDTO;
import org.acme.infraestructure.output.DB.DTO.ProductDb;
import org.acme.infraestructure.output.DB.DTO.ProductDbDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StoredProcedureRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void savedFactura(FacturaDb facturaDb){
        StoredProcedureQuery saveFacturaQuery = entityManager.createStoredProcedureQuery("saveBill",FacturaDb.class)
                .registerStoredProcedureParameter(1,float.class,ParameterMode.IN)
                .registerStoredProcedureParameter(2, float.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3,float.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4,float.class,ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class,ParameterMode.IN)
                .setParameter(1, facturaDb.getFacturaSubtotal())
                .setParameter(2, facturaDb.getTotalDiscount())
                .setParameter(3, facturaDb.getFacturaTax())
                .setParameter(4, facturaDb.getTotal())
                .setParameter(5,facturaDb.getId().toString());
        saveFacturaQuery.executeUpdate();

        for(ProductDb p : facturaDb.getProducts()){
            StoredProcedureQuery saveProductsQuery = entityManager.createStoredProcedureQuery("saveProducts", ProductDb.class)
                    .registerStoredProcedureParameter(1, float.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, float.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, float.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(1, p.getDiscountTotal())
                    .setParameter(2,p.getDiscountPercentage())
                    .setParameter(3,p.getFinalPrice())
                    .setParameter(4,p.getCategory())
                    .setParameter(5,p.getBrand())
                    .setParameter(6,p.getTitle())
                    .setParameter(7,p.getThumbnail())
                    .setParameter(8,facturaDb.getId().toString());
            saveProductsQuery.execute();
        }
    }

    @Transactional
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
