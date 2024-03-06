package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CheckoutAdapter;
import com.example.ecommerce.main.adapters.OrderAdapter;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.repository.models.localdata.Order;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;

public class MyOrder extends AppCompatActivity {
RecyclerView recyclerView;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        recyclerView=findViewById(R.id.cartrecycle);
        back=findViewById(R.id.backbuttoncart);
        String check=Mainscreen.sharedPreferences.getString(Mainscreen.order,null);
        if(!(check==null)) {
   ArrayList<Order> temp= Helper.getOrders();
//        Log.d("checking",Integer.toString( temp.get(0).getIndex()));
    OrderAdapter adapter=new OrderAdapter(MyOrder.this,temp);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);}

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyOrder.this,UserProfile.class);
                startActivity(intent);
            }
        });
    }
}