package com.example.ecommerce.main.repository.models.remotedata;

import java.io.Serializable;
import java.util.ArrayList;

public class Products  {



        public int id;
        public String title;
        public float price;
        public String description;
        public Category category;
        public ArrayList<String> images;

    public Products(int id, String title, float price, String description, Category category, ArrayList<String> images) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.images = images;
    }

    public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public float getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }

        public Category getCategory() {
            return category;
        }

        public ArrayList<String> getImages() {
            return images;
        }
    }


