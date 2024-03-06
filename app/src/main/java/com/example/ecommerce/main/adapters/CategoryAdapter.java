package com.example.ecommerce.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.ui.FilteredProducts;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> implements Filterable {
    Context ctx;
    List<Category> categoryList;
    private ArrayList<Category> clone;

    public CategoryAdapter(Context ctx, List<Category> categoryList) {
        this.ctx = ctx;
        this.categoryList = categoryList;
        clone=new ArrayList<>(categoryList);
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.categorycell,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        holder.Categoryname.setText(categoryList.get(position).getName());
        Glide.with(ctx)
                .load(categoryList.get(position).getImage())
                .into(holder.CategoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int postemp= holder.getAdapterPosition();
               int idtemp=categoryList.get(postemp).getId();
                Intent intent=new Intent(ctx, FilteredProducts.class);
intent.putExtra("name",categoryList.get(postemp).getName());
                intent.putExtra("id",idtemp);
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView Categoryname;
        public ImageView CategoryImage;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            this.Categoryname=itemView.findViewById(R.id.categoryname);
            this.CategoryImage=itemView.findViewById(R.id.categorypic);
        }
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList <Category> newList = new ArrayList<>(); // create new list
            if(constraint == null || constraint.length() ==0){
                newList.addAll(clone);
            }else{
                String FilterPattern = constraint.toString().toLowerCase().trim();
                for(Category i : clone){
                    if(i.getName().toLowerCase().contains(FilterPattern)){
                        newList.add(i);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = newList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categoryList.clear();
            categoryList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
