package com.example.ecommerce.main.repository.models.remotedata;

public class Users {
    public int id;
    public String email;
    public String password;
    public String name;
    public String role;
    public String avatar;

    public int getId() {
        return id;
    }

    public Users() {

    }


    public Users( String name,String email, String password,String x) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar=x;


    }

    public Users(int id, String email, String name, String role, String avatar) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}
