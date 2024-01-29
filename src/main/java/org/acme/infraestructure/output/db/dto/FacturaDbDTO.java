package org.acme.infraestructure.output.db.dto;

import java.util.List;

public record FacturaDbDTO(double subtotal,
                           double discountAmount,
                           double taxes,
                           double amountToPay,
                           List<ProductDbDTO> products) {
}
