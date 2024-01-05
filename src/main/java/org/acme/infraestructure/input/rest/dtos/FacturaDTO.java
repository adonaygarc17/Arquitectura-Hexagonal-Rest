package org.acme.infraestructure.input.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonPropertyOrder({"products",  "totalDiscount","facturaTax","facturaSubtotal","total"})
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

    @JsonProperty("facturaSubtotal")
    public double getFacturaSubtotal() {
        return facturaSubtotal;
    }

    @JsonProperty("facturaDescuento")
    public double getTotalDiscount() {
        return totalDiscount;
    }

    @JsonProperty("facturaImpuestos")
    public double getFacturaTax() {
        return facturaTax;
    }


    @JsonProperty("productos")
    public List<FacturaProductDTO> getProducts() {
        return products;
    }


    @JsonProperty("facturaTotal")
    public double getTotal() {
        return total;
    }

}
