package com.example.nguyentanlinh_2122110398.model;

public class Product {
    private String image;
    private String name;
    private String price;
    private String rating;
    private String quantity;
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Product(String image, String name, String price, String rating, String quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
    }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getQuantity() {
        return quantity;
    }
}
