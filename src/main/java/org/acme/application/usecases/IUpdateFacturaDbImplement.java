package org.acme.application.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.acme.application.interfaces.input.IFacturaService;
import org.acme.application.interfaces.input.IUpdateFacturaDb;
import org.acme.domain.entities.Factura;
import org.acme.domain.entities.Product;
import org.acme.infraestructure.output.db.dto.FacturaDb;
import org.acme.infraestructure.output.db.dto.ProductDb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class IUpdateFacturaDbImplement implements IUpdateFacturaDb {

    @Inject
    IFacturaService facturaService;

    @Override
    public FacturaDb updateFactura(UUID id, int limit, int skip) {
        FacturaDb updateFactura = FacturaDb.findById(id);

        if(updateFactura == null){
            throw new NotFoundException();
        }
        ProductDb.delete("facturaDb",updateFactura);
        Factura newProduct = facturaService.generarFactura(limit, skip);
        updateFactura.setId(id);
        updateFactura.setFacturaSubtotal(newProduct.getFacturaSubTotal());
        updateFactura.setFacturaTax(newProduct.getFacturaTax());
        updateFactura.setTotalDiscount(newProduct.getTotalDiscount());
        updateFactura.setTotal(newProduct.getTotal());
        updateFactura.setProducts(mappingProductsDb(newProduct.getProducts(),updateFactura));
        updateFactura.persist();
        return updateFactura;
    }

    public List<ProductDb> mappingProductsDb(List<Product> products, FacturaDb factura){
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
