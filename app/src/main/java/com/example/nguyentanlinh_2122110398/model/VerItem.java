package com.example.nguyentanlinh_2122110398.model;

public class VerItem {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    public class Rating {
        private double rate;
        private int count;

        public double getRate() {
            return rate;
        }

        public int getCount() {
            return count;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public Rating getRating() {
        return rating;
    }

}
