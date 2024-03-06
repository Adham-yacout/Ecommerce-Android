package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.viewmodel.CategorizedViewModel;
import com.example.ecommerce.R;
import com.example.ecommerce.main.adapters.CategoryAdapter;
import com.example.ecommerce.main.adapters.ChoosenFilterAdapter;
import com.example.ecommerce.main.viewmodel.ProductsViewModel;

import java.util.List;


public class FilteredProducts extends AppCompatActivity {
RecyclerView recyclerView;
CategorizedViewModel CategorizedView;
ProductsViewModel productsViewModel;
ImageView back;
TextView txtcatg;
SearchView searchView;
    ChoosenFilterAdapter adapter;
  //  CategorizedViewModel CategorizedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_products);
        recyclerView=findViewById(R.id.filteredrecycle);
        back=findViewById(R.id.Backbtn);
        txtcatg=findViewById(R.id.TextCategory);
        searchView=findViewById(R.id.Searchbarfilter);
        Intent intent =getIntent();
        Log.i("temp", "onResponse1:   ");
        int id=intent.getExtras().getInt("id");
        String name=intent.getExtras().getString("name");
txtcatg.setText(name);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

        CategorizedView = new ViewModelProvider(this).get(CategorizedViewModel.class);
        CategorizedView.getCategories(id).observe(this, Category->{
             adapter=new  ChoosenFilterAdapter(FilteredProducts.this,Category,id);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(FilteredProducts.this,2));
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

//        CategoryViewModel.getCategories(this).observe(this, Category->{
//            CategoryAdapter adapter=new CategoryAdapter(Redirection.this,Category);
//            myRecycler.setAdapter(adapter);
//            myRecycler.setLayoutManager(new GridLayoutManager(Redirection.this,2));


}
}