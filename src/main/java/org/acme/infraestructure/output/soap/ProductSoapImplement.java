package org.acme.infraestructure.output.soap;

/*import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.SneakyThrows;
import net.gbm.adapter.ISoapAdapter;
import net.gbm.adapter.ProductDTO;
import org.acme.application.interfaces.output.IProducts;
import org.acme.domain.entities.Product;
import net.gbm.adapter.CustomRestResponseDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductSoapImplement  {

    @CXFClient("myProducts")
    ISoapAdapter soapAdapter;

    @SneakyThrows
    //@Override
    public List<Product> obtenerProducts(int arg0, int arg1 ){
        CustomRestResponseDTO pro = soapAdapter.getProducts(arg0, arg1);

        ArrayList<Product> productsList = new ArrayList<>();

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
            productsList.add(product);
        }
        return productsList;

    }
}
*/