package com.example.ecommerce.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.ui.FilteredProducts;
import com.example.ecommerce.main.ui.ItemPage;
import com.example.ecommerce.main.util.Helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoosenFilterAdapter extends RecyclerView.Adapter<ChoosenFilterAdapter.MyViewHolder> implements Filterable {
    Context ctx;
    List<Products> productschoosenlist;
    List<Products> productschoosenlisttemp=new ArrayList<>();
    List<Products> productschoosenlistorig=new ArrayList<>();
    private ArrayList<Products> clone;
    int id;
    public ChoosenFilterAdapter(Context ctx, List<Products> productschoosenlist,int id) {
        this.ctx = ctx;
        this.id=id;

        for(int i=0;i<productschoosenlist.size();i++)
        {
            if((productschoosenlist.get(i).getCategory().getId()==id))
            {
             // productschoosenlist.remove(i);
              productschoosenlisttemp.add(productschoosenlist.get(i));

            }
        }
        productschoosenlist=productschoosenlisttemp;
        clone=new ArrayList<>(productschoosenlist);
        for(int i=0;i<productschoosenlist.size();i++)
        {
            Log.i("checker",Integer.toString( productschoosenlist.get(i).getCategory().getId()));
        }
        this.productschoosenlist = productschoosenlist;
    }

    @NonNull
    @Override
    public ChoosenFilterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.choosenfiltercell,parent,false);

       ChoosenFilterAdapter.MyViewHolder viewHolder = new MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoosenFilterAdapter.MyViewHolder holder, int position) {
        String Title=productschoosenlist.get(position).getTitle();
        String desc=productschoosenlist.get(position).getDescription();
        String caty= Integer.toString (productschoosenlist.get(position).getCategory().getId());
holder.namefilter.setText(productschoosenlist.get(position).getTitle());
holder.descfilter.setText(productschoosenlist.get(position).getDescription());

        holder.pricefilter.setText(String.valueOf( productschoosenlist.get(position).getPrice())+" $");
        Glide.with(ctx).load(productschoosenlist.get(position).getImages().get(0))
                .into(holder.imagefilter);
        int idchosen=productschoosenlisttemp.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category cat=productschoosenlist.get(holder.getAdapterPosition()).getCategory();
ArrayList<String> imageslist=productschoosenlist.get(holder.getAdapterPosition()).getImages();
float  price=productschoosenlist.get(position).getPrice();
                Log.i("id",Integer.toString(idchosen)+"target");
              Intent intent=new Intent(ctx, ItemPage.class);
               intent.putExtra("id2",idchosen);
//               intent.putExtra("title",Title);
//               intent.putExtra("price",price);
//                intent.putExtra("desc",desc);
//                intent.putExtra("category",caty);
                Products product=new Products(idchosen,Title,price,desc,cat,imageslist);
                Helper.Universalproduct=new Products(idchosen,Title,price,desc,cat,imageslist);
//                Bundle args = new Bundle();
//
//                args.putSerializable("ARRAYLIST",(Serializable)imageslist);
//                intent.putExtra("BUNDLE",args);
//
//
//                intent.putExtra("BUNDLE",args);

               ctx.startActivity(intent);
            }
        });

    }
//    public int getidcorrect(String title)
//    {
//        return;
//    }

    @Override
    public int getItemCount() {
        return productschoosenlist.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public ImageView imagefilter;
        public TextView namefilter;
        public TextView descfilter;
        public TextView pricefilter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagefilter=itemView.findViewById(R.id.choosenfilterpic);
            namefilter=itemView.findViewById(R.id.choosenfiltername);
            descfilter=itemView.findViewById(R.id.choosenfilterdescp);
            pricefilter=itemView.findViewById(R.id.specialpricefilter);

        }
    }
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList <Products> newList = new ArrayList<>(); // create new list
            if(constraint == null || constraint.length() ==0){
                newList.addAll(clone);
            }else{
                String FilterPattern = constraint.toString().toLowerCase().trim();
                for(Products i : clone){
                    if(i.getTitle().toLowerCase().contains(FilterPattern)){
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
            productschoosenlist.clear();
            productschoosenlist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
