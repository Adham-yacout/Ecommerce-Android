package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.localdata.CreditCard;
import com.example.ecommerce.main.util.Helper;

public class Payment extends AppCompatActivity {
ImageView backbtn;
EditText card,cvv,expire;
Button cancel;
Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        backbtn=findViewById(R.id.Backbtn);
        cancel=findViewById(R.id.Cancelbtn);
        card=findViewById(R.id.Cardnumber);
        cvv=findViewById(R.id.Cvv);
        expire=findViewById(R.id.Cardexpire);
        confirm=findViewById(R.id.confirmbutton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(card.getText().toString().isEmpty()||
                cvv.getText().toString().isEmpty()||
                        expire.getText().toString().isEmpty()
                )) {
                    boolean matches = expire.getText().toString().matches("\\d{2}/\\d{2}");
                    if (matches) {
                        Helper.creditCard = new CreditCard(card.getText().toString(),
                                cvv.getText().toString(),
                                expire.getText().toString());

                        setCard();
                        Helper.Activeuser.setCard(Helper.creditCard);
                        Toast.makeText(Payment.this, "Card added succesfully", Toast.LENGTH_SHORT).show();
                        cvv.setText("");
                        expire.setText("");
                        card.setText("");
                        String x = "0";
                        try {
                            x = (String) getIntent().getExtras().get("key");
                        } catch (Exception e) {
                            x = "0";
                        }

                        if (x.equals("1")) {
                            Intent intent = new Intent(Payment.this, Mycart.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Payment.this, UserProfile.class);
                            startActivity(intent);
                        }

                    }
                    else {
                        Toast.makeText(Payment.this,"please fill date with format (dd/mm)",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Payment.this,"please fill all required fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public  void setCard()
    {

        Mainscreen.sharedPreferences=getSharedPreferences(Mainscreen.shared,MODE_PRIVATE);
        Mainscreen.editor.putString(Mainscreen.card,Helper.creditCard.getCardnumber());
        Mainscreen. editor.putString(Mainscreen.expire,Helper.creditCard.getExpdate());
        Mainscreen.editor.putString(Mainscreen.cvv, Helper.creditCard.getCvv());

        Mainscreen.editor.apply();

    }
}