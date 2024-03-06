package com.example.ecommerce.main.retrofit;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomRetro {
    private static CustomRetro instance = null;

    private  API myApi;

    private CustomRetro(int id)  {

        String producturl="https://api.escuelajs.co/api/v1/products/"+Integer.toString(id);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(producturl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(API.class);


    }

    public static synchronized CustomRetro getInstance(int id)  {
        instance=null;
        if (instance == null){
            instance = new CustomRetro(id);
        }
        return instance;
    }

    public API getMyApi() {
        return myApi;
    }
}
