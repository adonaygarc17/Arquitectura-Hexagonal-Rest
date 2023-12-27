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

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonbProperty("titulo")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JsonbProperty("categoria")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @JsonbProperty("marca")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    @JsonbProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    @JsonbProperty("porcentajeDescuento")
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @JsonbProperty("montoDescuento")
    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @JsonbProperty("precioFinal")
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
