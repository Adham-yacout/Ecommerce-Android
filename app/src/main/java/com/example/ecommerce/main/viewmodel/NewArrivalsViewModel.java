package com.example.ecommerce.main.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArrivalsViewModel extends ViewModel {
    private MutableLiveData<List<Products>> productslist;
    public MutableLiveData<List<Products>> getnew(Context ctx) {

        if (productslist== null) {
            productslist = new MutableLiveData<>();
            loadProducts();
        }
        return productslist;
    }

    private void loadProducts() {
        Call<List<Products>> call = RetrofitClient.getInstance().getMyApi().getnew();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful()){

                }else {
                    productslist.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }
}
