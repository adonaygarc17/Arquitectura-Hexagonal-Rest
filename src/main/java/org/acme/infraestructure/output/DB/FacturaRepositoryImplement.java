package org.acme.infraestructure.output.DB;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.application.interfaces.output.IFacturaRepository;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.output.DB.DTO.FacturaDb;
import org.acme.infraestructure.output.DB.DTO.ProductDb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class FacturaRepositoryImplement implements IFacturaRepository {

   @Inject
   StoredProcedureRepository storedProcedureRepository;

    @Override
    public void saveFactura(Factura factura) {
        FacturaDb facturaDb = new FacturaDb();
        facturaDb.setId(UUID.randomUUID());
        facturaDb.setFacturaTax((float)factura.getFacturaTax());
        facturaDb.setFacturaSubtotal((float)factura.getFacturaSubTotal());
        facturaDb.setTotalDiscount((float)factura.getTotalDiscount());
        facturaDb.setTotal((float)factura.getTotal());
        facturaDb.setProducts(mappingProducts(factura.getProducts(),facturaDb));
        storedProcedureRepository.savedFactura(facturaDb);
    }
    public List<ProductDb> mappingProducts(List<Product> products, FacturaDb factura){
        ArrayList<ProductDb> dbProducts = new ArrayList<>();
        for (Product p : products) {
            ProductDb dbProduct = new ProductDb();
            dbProduct.setTitle(p.getTitle());
            dbProduct.setCategory(p.getCategory());
            dbProduct.setBrand(p.getBrand());
            dbProduct.setThumbnail(p.getThumbnail());
            dbProduct.setDiscountPercentage(p.getDiscountPercentage());
            dbProduct.setDiscountTotal(p.getDiscountTotal());
            dbProduct.setFinalPrice(p.getFinalPrice());
            dbProduct.setFacturaDb(factura);
            dbProducts.add(dbProduct);
        }
        return  dbProducts;
    }

}
