package com.example.ecommerce.main.retrofit;

import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.repository.models.remotedata.Users;

import java.util.List;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {


    String BASE_URL = " https://api.escuelajs.co/api/v1/";
    @GET("users")
    Call<List<Users>> getUsers();
    @GET("products")
    Call<List<Products>> getProducts();
    @GET("categories")
    Call <List<Category>> getcategories();
//    @GET("/")
//    Call <List<Products>> getsingleproduct();


    @POST("users/")
    Call <Users> adduseer(
       @Body Users users
    );
    @GET("products/{id}")
    Call<Products> getsingleproduct(@Path("id") int postid);
    @GET("products?offset=10&limit=10")
    Call<List<Products>> getnew();
//    @GET("products/{id}")
//  @Path("id")
//Call <List<Products>>
}

