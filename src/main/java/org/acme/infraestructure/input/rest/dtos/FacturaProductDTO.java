package org.acme.infraestructure.input.rest.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","title","description","price","discountPercentage", "stock", "brand","category", "thumbnail" })
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

    @JsonProperty("precio")
    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @JsonProperty("titulo")
    public String getTitle() {
        return title;
    }

    @JsonProperty("categoria")
    public String getCategory() {
        return category;
    }

    @JsonProperty("marca")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("porcentajeDescuento")
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    @JsonProperty("montoDescuento")
    public double getDiscountAmount() {
        return discountAmount;
    }

    @JsonProperty("precioFinal")
    public double getFinalPrice() {
        return finalPrice;
    }

}
