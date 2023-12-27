package org.acme.domain.entities;

import java.text.DecimalFormat;
import java.util.Objects;


public class Product {

    public int id;

    public String title;

    public  String category;

    public String thumbnail;

    public double discountPercentage;

    public int stock;

    public String brand;

    public double price;

    public  double discountTotal;

    public double tax;

    public double subtotal;

    public double finalPrice;

    public Product(int id, String title, String category, String thumbnail,
                   double discountPercentage, int stock, String brand, double price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.thumbnail = thumbnail;
        this.discountPercentage = discountPercentage;
        this.stock = stock;
        this.brand = brand;
        this.price = price;
        this.discountTotal = calculateDiscountTotal(price,discountPercentage);
        this.subtotal = calculateSubTotal(price, discountTotal);
        this.tax = calculateTaxProduct(price,this.discountTotal,category);
        this.finalPrice = calculateFinalPrice(price, discountTotal, tax);
    }

    public static double calculateSubTotal(double price, double discountTotal) {

        return toDecimal(price - discountTotal);
    }

    public static double calculateDiscountTotal(double price, double discountPercentage){
        return toDecimal(price * (discountPercentage/100));
    }

    public static double calculateTaxProduct (double price, double discountTotal, String category){
        if (Objects.equals(category.toLowerCase(), "skincare")){
            return toDecimal((price - discountTotal) * 0.04);
        } else if (Objects.equals(category.toLowerCase(), "groceries")) {
           return  toDecimal((price - discountTotal) * 0.08);
        }else {
            return toDecimal((price - discountTotal) * 0.13);
        }
    }

    public static double calculateFinalPrice (double price, double discountTotal, double tax){
        return toDecimal(price - discountTotal + tax);
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

    public double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }


    public static double toDecimal(double n){
        DecimalFormat df =  new DecimalFormat("###.##");
        return Double.parseDouble(df.format(n));
    }
}
