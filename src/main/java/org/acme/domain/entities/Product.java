package org.acme.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.Objects;

@Getter
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

    public static double toDecimal(double n){
        DecimalFormat df =  new DecimalFormat("###.##");
        return Double.parseDouble(df.format(n));
    }
}
