package org.acme.infraestructure.output.Soap;

import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;
import net.gbm.adapter.ISoapAdapter;
import net.gbm.adapter.JsonProcessingException_Exception;
import net.gbm.adapter.ProductDTO;
import org.acme.application.interfaces.output.IProductSoap;
import org.acme.domain.entities.Product;
import net.gbm.adapter.CustomRestResponseDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductSoapImplement implements IProductSoap {

    @CXFClient("myProducts")
    ISoapAdapter soapAdapter;

    @Override
    public List<Product> obtenerProducts(int arg1, int arg0) throws JsonProcessingException_Exception {
        CustomRestResponseDTO pro = soapAdapter.getProducts(arg1, arg0);

        ArrayList<Product> ProductsList = new ArrayList<>();

        for (ProductDTO p: pro.getProducts().getProduct()){
            Product product = new Product(
                    p.getId(),
                    p.getTitle(),
                    p.getDetails().getCategory(),
                    p.getThumbnail(),
                    p.getDiscountPercentage(),
                    p.getStock().getQuantity(),
                    p.getDetails().getBrand(),
                    p.getPrice()
            );
            ProductsList.add(product);
        }
        return ProductsList;

    }
}
