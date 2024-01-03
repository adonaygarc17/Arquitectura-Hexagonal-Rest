package org.acme.domain.entities;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProductTest {

    @Test
    void whenCalculateSubTotalWhenRecivedPriceDiscount() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }

    @Test
    void shouldCalculateDiscountTotalWhen() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }

    @Test
    void shouldCalculateTaxWhenRecivedProduct() {
        double tax = Product.calculateTaxProduct(239,28.68,"Smartphone");
        double respuesta = 27.34;
        assertEquals(tax,respuesta);
    }
    @Test
    void shouldCalculateTaxProductWhenGroseryCategory(){
        double tax = Product.calculateTaxProduct(239,28.68,"groceries");
        double respuesta = 16.83;
        assertEquals(tax,respuesta);
    }
    @Test
    void shouldCalculateTaxProductWhenSkincareCategory(){
        double tax = Product.calculateTaxProduct(239,28.68,"skincare");
        double respuesta = 8.41;
        assertEquals(tax,respuesta);
    }
    @Test
    void shouldCalculateFinalPriceWhenPriceDiscountPercentage() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }
    @Test
    void shouldCalculateAllWhenRecivedProduct(){
        Product p = new Product(1,"iPhone9","smartphones",
                "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
                12,3,"Apple",549);
        assertAll(
                () -> assertEquals(65.88,p.getDiscountTotal()),
                () -> assertEquals(483.12,p.getSubtotal()),
                () -> assertEquals(62.81,p.getTax()),
                () ->assertEquals(545.93,p.getFinalPrice())
        );
    }
}