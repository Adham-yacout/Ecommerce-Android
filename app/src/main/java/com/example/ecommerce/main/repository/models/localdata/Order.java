package com.example.ecommerce.main.repository.models.localdata;

import com.example.ecommerce.main.ui.Address;

import java.util.ArrayList;

public class Order {
   private int index;
    private ArrayList<Cart> cart;
    private ArrayList<AddressData> address;
    private ArrayList<CreditCard> card;
 private String titles;
 private String price;

    public Order(ArrayList<Cart> cart, ArrayList<AddressData> address, ArrayList<CreditCard> card) {
        this.cart = cart;
        this.address = address;
        this.card = card;


    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getTitles() {
        return titles;
    }

    public int getIndex() {
        return index;
    }

    public Order(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

}
