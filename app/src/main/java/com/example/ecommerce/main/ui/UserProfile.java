package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.util.Helper;

public class UserProfile extends AppCompatActivity {
ImageView back,userprofile,logout;
TextView username,useremail;
Button contact,card,personnaldetails,gotoship,fav,orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        back=findViewById(R.id.Backbtn);
        contact=findViewById(R.id.contactus);
        card=findViewById(R.id.Carddetails);
        gotoship=findViewById(R.id.gotoshipping);
        fav=findViewById(R.id.gotofav);
        logout=findViewById(R.id.signout);
    orders=findViewById(R.id.Myordergoto);
        username=findViewById(R.id.UserName);
        personnaldetails=findViewById(R.id.Personnaldetails);
        useremail=findViewById(R.id.Useremail);
        userprofile=findViewById(R.id.UserPicture);
        username.setText(Helper.Activeuser.getName());

        useremail.setText(Helper.Activeuser.getEmail());
        Glide.with(UserProfile.this).load(Helper.Activeuser.getAvatar()).into(userprofile);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,MyOrder.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to sign out?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent=new Intent(UserProfile.this,Mainscreen.class);

                        Mainscreen.sharedPreferences=getSharedPreferences(Mainscreen.shared,MODE_PRIVATE);
                        Mainscreen.editor.putString(Mainscreen.order,null);
                        Mainscreen.editor.clear();
                        Mainscreen. editor.apply();


                        dialog.dismiss();
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(UserProfile.this,MyFav.class);
        startActivity(intent);
    }
});
        personnaldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,UserDetails.class);
                startActivity(intent);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Helper.Activeuser.getCard()== null)
                {
                    Intent intent=new Intent(UserProfile.this,Payment.class);
                    startActivity(intent);
                }
                else{
                    new AlertDialog.Builder(UserProfile.this)
                            .setTitle("Update")
                            .setMessage("You Have already Entered a Payment Card do you want to update it?")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent=new Intent(UserProfile.this,Payment.class);
                                    dialog.dismiss();
                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            })

                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }
        });
        gotoship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this,Address.class);
                startActivity(intent);

            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","adham.yacout@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(UserProfile.this,HomeScreen.class);
        startActivity(intent);

    }
});

    }
}