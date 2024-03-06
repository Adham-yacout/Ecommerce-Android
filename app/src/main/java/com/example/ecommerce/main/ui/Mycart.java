package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.adapters.CartAdapter;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;

public class Mycart extends AppCompatActivity {
RecyclerView recyclerView;
public static TextView price;
ImageView back;
Button checkout;
    public static TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        recyclerView=findViewById(R.id.cartrecycle);
        price=findViewById(R.id.totalpricecart);
        amount=findViewById(R.id.textalig);
        ArrayList<Cart> temp= Helper.GetCart();
        CartAdapter adapter=new CartAdapter(Mycart.this,temp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        checkout=findViewById(R.id.proceedcheckout);

        back=findViewById(R.id.Backbtncart);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

checkout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(temp.size()==0)
        {
            Toast.makeText(Mycart.this,"Cart is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            if(Helper.Activeuser.getCard()==null)
            {
                new AlertDialog.Builder(Mycart.this)
                        .setTitle("Card missing")
                        .setMessage("Please enter card to cntinue")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Mycart.this, Payment.class);
                                intent.putExtra("key","1");
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
            else if(Helper.Activeuser.getAddressData()==null){
                new AlertDialog.Builder(Mycart.this)
                        .setTitle("Address missing")
                        .setMessage("Please enter Address to continue")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(Mycart.this, Address.class);
                                intent.putExtra("key","1");
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
            else {
                Intent intent=new Intent(Mycart.this,Checkout.class);

                startActivity(intent);
            }
        }

    }
});


    }
    public  static void pricesetter ()
    {
        price.setText(String.valueOf( Helper.getTotalprice())+"$");
        amount.setText("Total items:"+ "(" + (String.valueOf(Helper.getTotalquantity()))+")");
    }
}