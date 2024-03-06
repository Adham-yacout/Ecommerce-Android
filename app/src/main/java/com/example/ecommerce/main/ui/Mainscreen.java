package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ecommerce.R;
import com.example.ecommerce.main.Database.CartHelper;
import com.example.ecommerce.main.Database.FavHelper;
import com.example.ecommerce.main.util.Helper;

public class Mainscreen extends AppCompatActivity {
Button login,signup;
    public static SharedPreferences sharedPreferences;
    public static  SharedPreferences.Editor editor;
    public static  final String shared="sharedprefs";
    public static  final String name="name";
    public static  final String emailuser="email";
    public static  final String id="id";
    public static  final String role="role";
    public static  final String avatar="link";
    public static  final String state="state";
    public static  final String street="street";
    public static  final String city="city";
    public static  final String phonenumber="phonenumber";
    public static  final String zipcode="zipcode";
    public static  final String country="country";
    public static  final String addressstate="addressstate";
    public static  final String card="cardnumber";
    public static  final String expire="expire";
    public static  final String cvv="cvv";
    public static  final String order="order";

    public static CartHelper dbHelper;
    public static FavHelper favHelper;

   ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        login=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.signupbtn);
       sharedPreferences=getSharedPreferences(shared,MODE_PRIVATE);
         editor=sharedPreferences.edit();
         String dir=sharedPreferences.getString("state","false");
        dbHelper = new CartHelper(Mainscreen.this);
        favHelper= new FavHelper(Mainscreen.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dir.equals("false")) {
                    Intent intent = new Intent(Mainscreen.this, LoginScreen.class);
                    startActivity(intent);
                }

            else{
                Intent intent = new Intent(Mainscreen.this,HomeScreen.class);
                Helper.setuser();
                Helper.setaddress();
                 Helper.retrieveordrer();
                 Helper.setCard();

                startActivity(intent);
            }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Mainscreen.this,SignUp.class);
                startActivity(intent);

            }
        });
    }
}