package com.example.coderbot;

public class Product {

    private String image;
    private String title;
    private String detail;


    public Product(){

    }

    public Product(String image, String title, String detail) {
        this.image = image;
        this.title = title;
        this.detail=detail;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
    public String getDetail() {
        return detail;
    }


}