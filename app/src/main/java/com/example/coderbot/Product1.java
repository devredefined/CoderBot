package com.example.coderbot;

public class Product1 {

    private String image;
    private String title;
    private String detail;


    public Product1(){

    }

    public Product1(String image, String title, String detail) {
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