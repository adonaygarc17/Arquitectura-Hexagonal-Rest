package org.acme.infraestructure.output.db;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.acme.application.interfaces.output.IFacturaRepository;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.output.db.dto.FacturaDb;
import org.acme.infraestructure.output.db.dto.ProductDb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FacturaRepositoryImplement implements IFacturaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveFactura(Factura factura) {
        FacturaDb facturaDb = new FacturaDb();
        facturaDb.setId(UUID.randomUUID());
        facturaDb.setFacturaTax((float)factura.getFacturaTax());
        facturaDb.setFacturaSubtotal((float)factura.getFacturaSubTotal());
        facturaDb.setTotalDiscount((float)factura.getTotalDiscount());
        facturaDb.setTotal((float)factura.getTotal());
        facturaDb.setProducts(mappingProducts(factura.getProducts(),facturaDb));
        savedFactura(facturaDb);
    }
    public List<ProductDb> mappingProducts(List<Product> products, FacturaDb factura){
        ArrayList<ProductDb> dbProducts = new ArrayList<>();
        for (Product p : products) {
            ProductDb dbProduct = new ProductDb();
            dbProduct.setTitle(p.getTitle());
            dbProduct.setCategory(p.getCategory());
            dbProduct.setBrand(p.getBrand());
            dbProduct.setThumbnail(p.getThumbnail());
            dbProduct.setDiscountPercentage((float) p.getDiscountPercentage());
            dbProduct.setDiscountTotal((float) p.getDiscountTotal());
            dbProduct.setFinalPrice((float) p.getFinalPrice());
           // dbProduct.setFacturaDb(factura);
            dbProducts.add(dbProduct);
        }
        return  dbProducts;
    }

    @Transactional
    public void savedFactura(FacturaDb facturaDb){
        StoredProcedureQuery saveFacturaQuery = entityManager.createStoredProcedureQuery("saveBill",FacturaDb.class)
                .registerStoredProcedureParameter(1,float.class, ParameterMode.IN)
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

}
