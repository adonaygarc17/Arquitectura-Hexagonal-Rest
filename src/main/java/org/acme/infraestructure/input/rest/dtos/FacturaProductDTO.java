package org.acme.infraestructure.input.rest.dtos;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"id","title","description","price","discountPercentage", "stock", "brand","category", "thumbnail" })
public class FacturaProductDTO {
    private int id;
    private String title;
    private String category;

    private Double price;
    private String brand;
    private String thumbnail;
    private double discountPercentage;
    private double discountAmount;
    private double finalPrice;

    public FacturaProductDTO(int id, String title, String category,Double price, String brand, String thumbnail, double discountPercentage, double discountAmount, double finalPrice) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.brand = brand;
        this.thumbnail = thumbnail;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.finalPrice = finalPrice;
    }

    @JsonbProperty("precio")
    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @JsonbProperty("titulo")
    public String getTitle() {
        return title;
    }

    @JsonbProperty("categoria")
    public String getCategory() {
        return category;
    }

    @JsonbProperty("marca")
    public String getBrand() {
        return brand;
    }

    @JsonbProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonbProperty("porcentajeDescuento")
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    @JsonbProperty("montoDescuento")
    public double getDiscountAmount() {
        return discountAmount;
    }

    @JsonbProperty("precioFinal")
    public double getFinalPrice() {
        return finalPrice;
    }

}
