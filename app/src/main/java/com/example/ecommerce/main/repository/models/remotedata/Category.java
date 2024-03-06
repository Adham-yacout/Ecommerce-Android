package com.example.ecommerce.main.repository.models.remotedata;

import java.util.List;

public class Category {
    public int id;
    public String name;
    public String image;
  public static List<Products> categorizedlist;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
