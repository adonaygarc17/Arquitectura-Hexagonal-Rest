package org.acme.infraestructure.output.DB.DTO;


public record ProductDbDTO(String dbBillId,
                           String title,
                           String category,
                           String brand,
                           String urlThumbnail,
                           double discountPercentage,
                           double discountAmount,
                           double finalPrice){
}
