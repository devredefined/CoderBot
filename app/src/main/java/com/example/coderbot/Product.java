package com.example.coderbot;

public class Product {

    private String image;
    private String title;
    private String detail;
    private String id;


    public Product(){

    }

    public Product(String image, String title, String detail, String id) {
        this.image = image;
        this.title = title;
        this.detail = detail;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
//    public Product(String image, String title, String detail) {
//        this.image = image;
//        this.title = title;
//        this.detail=detail;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//    public String getDetail() {
//        return detail;
//    }


}