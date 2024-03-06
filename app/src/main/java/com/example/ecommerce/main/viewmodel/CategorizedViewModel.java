package com.example.ecommerce.main.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.retrofit.RetrofitClient;

import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorizedViewModel extends ViewModel {
    public MutableLiveData< List<Products>> finallist;
   public  List<Products> templist=null;
    private MutableLiveData< List<Products>> categorizedlist;
    public MutableLiveData< List<Products>> getCategories(int idtemp) {

        if (categorizedlist== null) {
            categorizedlist = new MutableLiveData<>();
             loadCategorized(idtemp);

        }

        return categorizedlist;
    }

    public void loadCategorized(int idtemp) {
        Call<List<Products>> call = RetrofitClient.getInstance().getMyApi().getProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>>response) {
                if(!response.isSuccessful()){

                }else {
                    categorizedlist.setValue(response.body());


                }

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.d("fail", t.toString());
            }
        });
        }
}
