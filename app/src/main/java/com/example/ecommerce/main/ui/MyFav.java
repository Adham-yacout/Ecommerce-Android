package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CartAdapter;
import com.example.ecommerce.main.adapters.FavAdapter;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.repository.models.localdata.Favourites;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;

public class MyFav extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fav);
        recyclerView=findViewById(R.id.cartrecycle);
        ArrayList<Favourites> temp= Helper.Getfav();
        FavAdapter adapter=new FavAdapter(MyFav.this,temp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        back=findViewById(R.id.Backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}