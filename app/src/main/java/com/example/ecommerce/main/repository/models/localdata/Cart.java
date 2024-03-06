package com.example.ecommerce.main.repository.models.localdata;

import com.example.ecommerce.main.repository.models.remotedata.Category;

import java.util.ArrayList;

public class Cart {
    private int Userid;
    private String email;
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String images;
    private int quantity;

    public Cart(int userid, String email, int id, String title, float price, String description, String category, String images, int quantity) {
        Userid = userid;
        this.email = email;
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.images = images;
        this.quantity = quantity;


    }





    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
