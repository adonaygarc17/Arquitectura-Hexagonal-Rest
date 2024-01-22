package org.acme.infraestructure.input.rest.dtos;


public class ProductDTO{
    public int id;

    public String title;

    public  String category;

    public String thumbnail;

    public double discountPercentage;

    public int stock;

    public String brand;

    public double price;

    public ProductDTO(int id, String title, String category, String thumbnail, double discountPercentage, int stock, String brand, double price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.thumbnail = thumbnail;
        this.discountPercentage = discountPercentage;
        this.stock = stock;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
