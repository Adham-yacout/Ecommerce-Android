package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommerce.R;
import com.example.ecommerce.main.Database.CartHelper;
import com.example.ecommerce.main.Database.FavHelper;
import com.example.ecommerce.main.repository.models.localdata.Order;

import java.util.ArrayList;
import java.util.List;

public class SuccessLogin extends AppCompatActivity {
Button enter;

    public static List<Order> orders=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_login);
        enter=findViewById(R.id.startshopping);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SuccessLogin.this,HomeScreen.class);
                Mainscreen.dbHelper.Emptycart();
                Mainscreen.favHelper.Emptycart();
                startActivity(intent);
            }
        });
    }
}