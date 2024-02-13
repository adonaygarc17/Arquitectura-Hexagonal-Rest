package org.acme.infraestructure.output.db.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FacturaDbDTO {
    public Number subtotal;
    public Number discountAmount;
    public Number taxes;
    public Number amountToPay;

    public List<ProductDbDTO> products;

    public FacturaDbDTO(Number subtotal, Number discountAmount, Number taxes, Number amountToPay, List<ProductDbDTO> products) {
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.taxes = taxes;
        this.amountToPay = amountToPay;
        this.products = products;
    }


}
