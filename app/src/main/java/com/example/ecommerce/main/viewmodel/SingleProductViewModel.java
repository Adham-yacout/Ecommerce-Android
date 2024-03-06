package com.example.ecommerce.main.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.retrofit.CustomRetro;
import com.example.ecommerce.main.retrofit.RetrofitClient;
import com.example.ecommerce.main.util.Helper;

import java.net.MalformedURLException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductViewModel extends ViewModel {

    private MutableLiveData<Products> Singleproductlist;
    public MutableLiveData<Products> getSingleProduct(int idtemp, Context ctx) {
        Singleproductlist=null;
        if (Singleproductlist== null) {
            Singleproductlist = new MutableLiveData<>();
           loadCategorized(idtemp,ctx);

        }

        return Singleproductlist;
    }

    public void loadCategorized(int idtemp,Context ctx) {
        ProgressDialog dialog=new Helper().myProgressDialog(ctx);
        dialog.show();
        Call<Products> call = RetrofitClient.getInstance().getMyApi().getsingleproduct(idtemp);
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products>  response) {
                if(!response.isSuccessful()){

                }else {
                    Singleproductlist.setValue(response.body());
                    dialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<Products>  call, Throwable t) {

            }
        });
    }
}

