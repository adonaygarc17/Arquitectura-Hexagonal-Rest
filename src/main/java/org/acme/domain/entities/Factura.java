package org.acme.domain.entities;

import java.text.DecimalFormat;
import java.util.List;

public class Factura {

    List<Product> products;

    double facturaSubTotal;

    double facturaTax;
    double totalDiscount;

    double total;

    public Factura(List<Product> products){
        List<Product> stockProducts = products.stream().filter(product -> product.getStock()>0).toList();
        this.products = stockProducts;
        calculateExtras(products);
    }

    private void calculateExtras(List<Product> products) {
        double discount = 0;
        double taxes = 0;
        double preTotal = 0;
        double subtotal = 0;
        for (Product p : products) {
            discount =  discount + p.getDiscountTotal();
            taxes = taxes + p.getTax();
            preTotal = preTotal + p.getFinalPrice();
            subtotal = subtotal + p.getSubtotal();
        }
        this.facturaSubTotal = toDecimal(subtotal);
        this.totalDiscount = toDecimal(discount);
        this.facturaTax = toDecimal(taxes);
        this.total = toDecimal(preTotal);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    public double getFacturaTax() {
        return facturaTax;
    }

    public void setFacturaTax(double facturaTax) {
        this.facturaTax = facturaTax;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getFacturaSubTotal() {
        return facturaSubTotal;
    }

    public void setFacturaSubTotal(double facturaSubTotal) {
        this.facturaSubTotal = facturaSubTotal;
    }

    public double toDecimal(double n){
        DecimalFormat df =  new DecimalFormat("###.##");
        return Double.parseDouble(df.format(n));
    }
}
