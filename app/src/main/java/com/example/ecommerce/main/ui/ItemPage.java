package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.example.ecommerce.main.Database.CartHelper;
import com.example.ecommerce.main.adapters.ChoosenFilterAdapter;
import com.example.ecommerce.main.adapters.ImageAdapter;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.retrofit.API;
import com.example.ecommerce.main.util.Helper;
import com.example.ecommerce.main.viewmodel.CategorizedViewModel;
import com.example.ecommerce.main.viewmodel.ProductsViewModel;
import com.example.ecommerce.main.viewmodel.SingleProductViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemPage extends AppCompatActivity implements Serializable {
    TextView title,desc,price,quantity;
   SingleProductViewModel singleProductViewModel;
    ViewPager images;
    ImageView backp,cart;
    Button add,minus,addcart;

public   String titleint,descint,userEmail,Userid,cat;
   public ArrayList<String> object;
    ImageView fav;
    public   int cont=0;

    public  float priceof1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);
        ViewPager viewPager = findViewById(R.id.viewpager);
        title=findViewById(R.id.Itemtitle);
        addcart=findViewById(R.id.Addtocart);
        backp=findViewById(R.id.backtocategory);
        desc=findViewById(R.id.Itemsdescription);
        price=findViewById(R.id.Itemprice);
        quantity=findViewById(R.id.ItemQuantity);
        fav=findViewById(R.id.addtofav);
        cart=findViewById(R.id.gotocart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemPage.this,Mycart.class);
                startActivity(intent);
            }
        });



        add=findViewById(R.id.Addbtn);
minus=findViewById(R.id.Minusbtn);
        Intent intent=getIntent();
        int id=intent.getExtras().getInt("id2");
        singleProductViewModel = new ViewModelProvider(this).get(SingleProductViewModel.class);
        singleProductViewModel.getSingleProduct(id,ItemPage.this).observe(this, Category->{
            title.setText(Category.getTitle());
            desc.setText(Category.getDescription());


           cat=Integer.toString(Category.getCategory().getId());
           object =Category.getImages();
            priceof1=Category.getPrice();
             titleint=Category.getTitle();
             descint=Category.getDescription();
             userEmail= Helper.Activeuser.getEmail();
             Userid= String.valueOf(Helper.Activeuser.getId());
            updateprice();

            ImageAdapter adapter = new ImageAdapter(this,Category.getImages());
            viewPager.setAdapter(adapter);


        });
backp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       finish();
    }
});
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      int temp= Integer.parseInt(quantity.getText().toString());

      temp=temp+1;
     quantity.setText(Integer.toString(temp));
      updateprice();
    }
});
minus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int temp= Integer.parseInt(quantity.getText().toString());
if(temp>1){temp=temp-1;}

        quantity.setText(Integer.toString(temp));
        updateprice();
    }
});
fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(cont==1)
        {
            fav.setColorFilter(getResources().getColor(R.color.black));
          Mainscreen.favHelper.deleteItem(String.valueOf(id));
            cont=0;
        }
        else{
            fav.setColorFilter(getResources().getColor(R.color.Red));
            Mainscreen.favHelper.addCart(ItemPage.this,Userid,userEmail,
                    Integer.toString(id),titleint,priceof1,
                    descint,cat,object.get(0));
            cont=1;
        }




    }
});



        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mainscreen.dbHelper.addCart(ItemPage.this,Userid,userEmail,
                      Integer.toString(id),titleint,priceof1,
                        descint,cat,object.get(0),quantity.getText().toString());
            }
        });
  }
    private void updateprice()
    {
        float itemprice= priceof1*(Float.parseFloat( quantity.getText().toString()));
        price.setText(Float.toString(itemprice)+" $");
    }
}