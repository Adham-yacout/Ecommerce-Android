package com.example.ecommerce.main.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ecommerce.main.repository.models.remotedata.Users;
import com.example.ecommerce.main.retrofit.RetrofitClient;
import com.example.ecommerce.main.util.Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersViewModel extends ViewModel {
    private MutableLiveData<List<Users>> userslist;

    public MutableLiveData<List<Users>> getUsers(Context ctx) {

        if (userslist == null) {
            userslist = new MutableLiveData<>();
            loadUsers(ctx);
        }
    return userslist;
    }

        private void loadUsers(Context ctx){
            ProgressDialog dialog=new Helper().myProgressDialog(ctx);
            Call<List<Users>> call = RetrofitClient.getInstance().getMyApi().getUsers();
            call.enqueue(new Callback<List<Users>>() {
                @Override
                public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                    if(!response.isSuccessful()){

                    }else {
                        userslist.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Users>> call, Throwable t) {

                }
            });

        }

}
