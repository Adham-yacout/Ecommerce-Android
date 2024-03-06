package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.util.Helper;

public class UserDetails extends AppCompatActivity {
ImageView back,UserImage;
TextView UserDetailsname,UserDetailsemail,UserDetailsRole;
TextView street,city,state,phonenumber,zipcode,country,card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        back=findViewById(R.id.Backbtndetails);
        UserImage=findViewById(R.id.UserImage);
        UserDetailsname=findViewById(R.id.UserDetailsname);
        UserDetailsemail=findViewById(R.id.UserDetailsemail);
        UserDetailsRole=findViewById(R.id.UserDetailsRole);
        street=findViewById(R.id.tempshipdetail);
        city=findViewById(R.id.tempcitydetail);
        state=findViewById(R.id.tempstatedetail);
        phonenumber=findViewById(R.id.tempphonedetail);
        zipcode=findViewById(R.id.tempzipdetail);
        country=findViewById(R.id.tempcountrydetail);
        card=findViewById(R.id.UserCardnumber);
        if(!(Helper.Activeuser.getAddressData()==null))
        {
            street.setText(Helper.Activeuser.getAddressData().getStreet());
            city.setText(Helper.Activeuser.getAddressData().getCity());
            state.setText(Helper.Activeuser.getAddressData().getState());
            phonenumber.setText(Helper.Activeuser.getAddressData().getPhonenumber());
            zipcode.setText(Helper.Activeuser.getAddressData().getZipcode());
            country.setText(Helper.Activeuser.getAddressData().getCountry());

        }
        if(!(Helper.Activeuser.getCard()==null))
        {
            String cardtemp=Helper.Activeuser.getCard().getCardnumber();
            cardtemp=Helper.replaceLastFour(cardtemp);
            card.setText(cardtemp);
        }

       Glide.with(this).load(Helper.Activeuser.getAvatar()).into(UserImage);
        UserDetailsname.setText(Helper.Activeuser.getName());
        UserDetailsRole.setText(Helper.Activeuser.getRole());
        UserDetailsemail.setText(Helper.Activeuser.getEmail());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



    }
}