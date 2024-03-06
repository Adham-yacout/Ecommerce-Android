package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CartAdapter;
import com.example.ecommerce.main.adapters.CheckoutAdapter;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.repository.models.localdata.Order;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {
RecyclerView recyclerView;
public static TextView price2;
ImageView back;
Button finishorder;
TextView stname,ctname,state,phone,zipcode,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        recyclerView=findViewById(R.id.cartrecycle);
        price2=findViewById(R.id.totalcheckoutprice);
        finishorder=findViewById(R.id.placeorder);
        back=findViewById(R.id.Backbtn);
stname=findViewById(R.id.streetname);
ctname=findViewById(R.id.cityname);
state=findViewById(R.id.state);
phone=findViewById(R.id.phonnenumber);
zipcode=findViewById(R.id.zipcode);
country=findViewById(R.id.country);
stname.setText("Street name:"+Helper.Activeuser.getAddressData().getStreet());
ctname.setText("city name:"+Helper.Activeuser.getAddressData().getCity());
state.setText("State:"+Helper.Activeuser.getAddressData().getState());
zipcode.setText("Zip code:"+Helper.Activeuser.getAddressData().getZipcode());
country.setText("Country:"+Helper.Activeuser.getAddressData().getCountry());
phone.setText("phone number:"+Helper.Activeuser.getAddressData().getPhonenumber());
        price2.setText(String.valueOf( Helper.getTotalprice())+"$");
        ArrayList<Cart> temp= Helper.GetCart();
        CheckoutAdapter adapter=new CheckoutAdapter(Checkout.this,temp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Checkout.this,HomeScreen.class);
                startActivity(intent);
            }
        });
        finishorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order=new Order(Helper.GetCart());
                Helper.Activeuser.setOrder(order);
                Helper.AddOrders(order);
                Mainscreen .dbHelper.Emptycart();
                Helper.updateorder();
                Intent intent=new Intent(Checkout.this,HomeScreen.class);
                startActivity(intent);
            }
        });
    }
    public  static void pricesetter ()
    {
        price2.setText(String.valueOf( Helper.getTotalprice())+"$");
    }
}