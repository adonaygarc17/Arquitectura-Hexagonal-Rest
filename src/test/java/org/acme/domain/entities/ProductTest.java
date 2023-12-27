package org.acme.domain.entities;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProductTest {

    @Test
    void calculateSubTotal() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }

    @Test
    void calculateDiscountTotal() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }

    @Test
    void calculateTaxProduct() {
        double tax = Product.calculateTaxProduct(239,28.68,"Smartphone");
        double respuesta = 27.34;
        assertEquals(tax,respuesta);
    }
    @Test
    void testCalculateTaxProductGrosery(){
        double tax = Product.calculateTaxProduct(239,28.68,"groceries");
        double respuesta = 16.83;
        assertEquals(tax,respuesta);
    }
    @Test
    void testCalculateTaxProductSkin(){
        double tax = Product.calculateTaxProduct(239,28.68,"skincare");
        double respuesta = 8.41;
        assertEquals(tax,respuesta);
    }

    @Test
    void calculateFinalPrice() {
        double descuento = Product.calculateDiscountTotal(239,12);
        double respuest = 28.68;
        assertEquals(respuest,descuento);
    }
}