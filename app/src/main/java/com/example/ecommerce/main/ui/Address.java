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
import com.example.ecommerce.main.repository.models.localdata.AddressData;
import com.example.ecommerce.main.util.Helper;

public class Address extends AppCompatActivity {
Button confirm;
ImageView back;
public EditText street,city,state,phonenumber,zipcode,country;
    String streettmp,citytmp,statetmp,phonenumbertmp,zipcodetmp,countrytmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        confirm=findViewById(R.id.Addressconfirm);
        street=findViewById(R.id.Addressstreet);
        city=findViewById(R.id.ADdresscity);
        state=findViewById(R.id.Addressstate);
        phonenumber=findViewById(R.id.AddressPhone);
        zipcode=findViewById(R.id.Addresszip);
        country=findViewById(R.id.Addresscountry);
        back=findViewById(R.id.Backbtnaddress);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                streettmp=street.getText().toString();
                citytmp=city.getText().toString();
                statetmp=state.getText().toString();
                phonenumbertmp=phonenumber.getText().toString();
                zipcodetmp=zipcode.getText().toString();
                countrytmp=country.getText().toString();
                    if (!(streettmp.isEmpty()||citytmp.isEmpty()||
                            statetmp.isEmpty()||phonenumbertmp.isEmpty()||
                            zipcodetmp.isEmpty()||
                            countrytmp.isEmpty())
                    )
                    {
                       Helper.addressData=new AddressData(streettmp,citytmp,statetmp,phonenumbertmp,zipcodetmp,countrytmp);
                        setaddress();
                   Helper.Activeuser.setAddressData(Helper.addressData);
                        String x="0";
                        try {
                            x= (String) getIntent().getExtras().get("key");
                        }
                        catch (Exception e)
                        {
                            x="0";
                        }

                        if(x.equals("1"))
                        {
                            Intent intent=new Intent(Address.this,Mycart.class);
                            startActivity(intent);
                        }
                        else {
                            Intent intent=new Intent(Address.this,UserProfile.class);
                            startActivity(intent);
                        }



                    }
                    else
                    {
                        Toast.makeText(Address.this,"Please enter all the data",Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
    public  void setaddress()
    {
        Mainscreen.sharedPreferences=getSharedPreferences(Mainscreen.shared,MODE_PRIVATE);
        Mainscreen.editor.putString(Mainscreen.street,Helper.addressData.getStreet());
        Mainscreen. editor.putString(Mainscreen.addressstate,Helper.addressData.getState());
        Mainscreen.editor.putString(Mainscreen.city, Helper.addressData.getCity());
        Mainscreen.editor.putString(Mainscreen.phonenumber,Helper.addressData.getPhonenumber());
        Mainscreen. editor.putString(Mainscreen.zipcode,Helper.addressData.getZipcode());
        Mainscreen.editor.putString(Mainscreen.country,Helper.addressData.getCountry());
        Mainscreen.editor.apply();
    }
}