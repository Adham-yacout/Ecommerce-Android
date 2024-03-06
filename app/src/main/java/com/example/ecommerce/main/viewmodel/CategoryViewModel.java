package com.example.ecommerce.main.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.retrofit.RetrofitClient;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<Category>> categorylist;




    public MutableLiveData<List<Category>> getCategories(Context ctx) {

        if (categorylist== null) {
            categorylist = new MutableLiveData<>();
            loadCategory(ctx);
        }

        return categorylist;
    }

    private void loadCategory(Context ctx) {
        ProgressDialog dialog=new Helper().myProgressDialog(ctx);
        dialog.show();
        Call<List<Category>> call = RetrofitClient.getInstance().getMyApi().getcategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(!response.isSuccessful()){

                }else {
                    categorylist.setValue(response.body());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }

}
