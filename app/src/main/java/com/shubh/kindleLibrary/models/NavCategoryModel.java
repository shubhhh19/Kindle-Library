package com.shubh.kindleLibrary.models;

public class NavCategoryModel {

    String name, description, stock, img_url;
    int availability;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String description, String stock, String img_url, int availability) {
        this.name = name;
        this.description = description;
        this.stock = stock;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
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
