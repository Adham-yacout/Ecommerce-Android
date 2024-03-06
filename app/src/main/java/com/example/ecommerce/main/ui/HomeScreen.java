package com.example.ecommerce.main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CategoryAdapter;
import com.example.ecommerce.main.adapters.ChoosenFilterAdapter;
import com.example.ecommerce.main.adapters.NewArrivals;
import com.example.ecommerce.main.util.Helper;
import com.example.ecommerce.main.viewmodel.CategoryViewModel;
import com.example.ecommerce.main.viewmodel.NewArrivalsViewModel;
import com.example.ecommerce.main.viewmodel.ProductsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;

public class HomeScreen extends AppCompatActivity {
BottomNavigationView navbar;
   ImageView categories,usergo;
   RecyclerView view1,view2;
   SearchView searchView;
    CategoryAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        navbar=findViewById(R.id.navbar);
        categories=findViewById(R.id.Mainmenu);
        view1=findViewById(R.id.reycle1);
        view2=findViewById(R.id.recycle2);
        searchView=findViewById(R.id.searchbar);

        usergo=findViewById(R.id.userdetailsgoto);
        Glide.with(this).load(Helper.Activeuser.getAvatar()).into(usergo);
        usergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,UserDetails.class);
                startActivity(intent);
            }
        });

    CategoryViewModel categoryViewModel= new ViewModelProvider(this).get(com.example.ecommerce.main.viewmodel.CategoryViewModel.class);
    categoryViewModel.getCategories(this).observe(this, Category-> {
                adapter1 = new CategoryAdapter(HomeScreen.this, Category);
                view1.setAdapter(adapter1);
                view1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            });
       NewArrivalsViewModel productsViewModel= new ViewModelProvider(this).get(NewArrivalsViewModel.class);
       productsViewModel.getnew(this).observe(this, products-> {
         NewArrivals adapter = new NewArrivals(HomeScreen.this, products);
            view2.setAdapter(adapter);
            view2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        });
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreen.this,Redirection.class);
                startActivity(intent);
            }
        });
        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {
break;
                    }
                    case R.id.cart: {
                        Intent intent=new Intent(HomeScreen.this,Mycart.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.person: {
                        Intent intent=new Intent(HomeScreen.this,UserProfile.class);
                        startActivity(intent);
                        break;


                    }
                }
                return false;
            }

        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter1.getFilter().filter(newText);

                return false;
            }
        });
    }
    }