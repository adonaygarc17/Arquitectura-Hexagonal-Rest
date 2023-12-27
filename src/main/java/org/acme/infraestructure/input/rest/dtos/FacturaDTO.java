package org.acme.infraestructure.input.rest.dtos;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.List;


@JsonbPropertyOrder({"products",  "totalDiscount","facturaTax","facturaSubtotal","total"})
public class FacturaDTO {

    private List<FacturaProductDTO> products;
    private double facturaSubtotal;
    private double totalDiscount;
    private double facturaTax;
    private double total;

    public FacturaDTO(List<FacturaProductDTO> products, double facturaSubtotal, double totalDiscount, double facturaTax, double total) {
        this.products = products;
        this.facturaSubtotal = facturaSubtotal;
        this.totalDiscount = totalDiscount;
        this.facturaTax = facturaTax;
        this.total = total;
    }

    @JsonbProperty("facturaSubtotal")
    public double getFacturaSubtotal() {
        return facturaSubtotal;
    }

    public void setFacturaSubtotal(double facturaSubtotal) {
        this.facturaSubtotal = facturaSubtotal;
    }
    @JsonbProperty("facturaDescuento")
    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
    @JsonbProperty("facturaImpuestos")
    public double getFacturaTax() {
        return facturaTax;
    }

    public void setFacturaTax(double facturaTax) {
        this.facturaTax = facturaTax;
    }

    @JsonbProperty("productos")
    public List<FacturaProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<FacturaProductDTO> products) {
        this.products = products;
    }




    @JsonbProperty("facturaTotal")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
