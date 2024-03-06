package com.example.ecommerce.main.repository.models.localdata;

public class UserExtras {
    private int id;
    private String email;
    private String name;
    private String role;
    private String avatar;
    private CreditCard card;
    private  Favourites fav;
    private Order order;
    private AddressData addressData;
//used for login to initiliaze active user
    public UserExtras(int id, String email, String name, String role, String avatar) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.avatar = avatar;
    }

    public AddressData getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getAvatar() {
        return avatar;
    }

    public CreditCard getCard() {
        return card;
    }

    public Favourites getFav() {
        return fav;
    }

    public Order getOrder() {
        return order;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public void setFav(Favourites fav) {
        this.fav = fav;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
