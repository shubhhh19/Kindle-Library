package com.shubh.kindleLibrary.models;

public class HomeCategory {

    String name, type, img_url;
    int availability;


    public HomeCategory() {
    }

    public HomeCategory(String name, String type, String img_url, int availability) {
        this.name = name;
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
