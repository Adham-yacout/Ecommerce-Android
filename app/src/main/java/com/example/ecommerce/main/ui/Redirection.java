package com.example.ecommerce.main.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CategoryAdapter;
import com.example.ecommerce.main.repository.models.remotedata.Category;

import com.example.ecommerce.main.viewmodel.CategoryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

public class Redirection extends AppCompatActivity {
    BottomNavigationView navbar;
    RecyclerView myRecycler;
    SearchView searchView;
    com.example.ecommerce.main.viewmodel.CategoryViewModel CategoryViewModel;
    CategoryAdapter adapter;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirection);
        myRecycler=findViewById(R.id.pagerecycle);
        navbar=findViewById(R.id.navbar);
        searchView=findViewById(R.id.searchcategory);
        back=findViewById(R.id.backbuttonred);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Redirection.this,HomeScreen.class);
                startActivity(intent);
            }
        });
        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home: {
                        Intent intent=new Intent(Redirection.this,HomeScreen.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.cart: {
                        Intent intent=new Intent(Redirection.this,Mycart.class);
                        startActivity(intent);
                    break;
                    }
                    case R.id.person: {
                        Intent intent=new Intent(Redirection.this,UserProfile.class);
                        startActivity(intent);
                        break;
                    }
                }
                return false;
            }

        });
    CategoryViewModel= new ViewModelProvider(this).get(com.example.ecommerce.main.viewmodel.CategoryViewModel.class);
    CategoryViewModel.getCategories(this).observe(this, Category->{
       adapter=new CategoryAdapter(Redirection.this,Category);
        myRecycler.setAdapter(adapter);
        myRecycler.setLayoutManager(new GridLayoutManager(Redirection.this,2));
    });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }

}