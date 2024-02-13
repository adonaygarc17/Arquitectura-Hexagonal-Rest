package org.acme.infraestructure.output.db.dto;


public record ProductDbDTO(String dbBillId,
                           String title,
                           String category,
                           String brand,
                           String urlThumbnail,
                           Number discountPercentage,
                           Number discountAmount,
                           Number finalPrice){
}
