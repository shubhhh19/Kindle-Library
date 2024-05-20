package com.shubh.kindleLibrary.models;

public class PopularModel {
    String name, description, rating, discount, type, img_url;
    int availability;

    public PopularModel() {
    }

    public PopularModel(String name, String description, String rating, String discount, String type, String img_url, int availability) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.discount = discount;
        this.type = type;
        this.img_url = img_url;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

}
