package com.example.ecommerce.main.repository.models.localdata;

public class AddressData {
    private String street;
    private String city;
    private String state;
    private String phonenumber;
    private String zipcode;
    private String country;

    public AddressData(String street, String city, String state, String phonenumber, String zipcode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.phonenumber = phonenumber;
        this.zipcode = zipcode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
