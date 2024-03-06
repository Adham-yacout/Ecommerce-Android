package com.example.ecommerce.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.remotedata.Category;
import com.example.ecommerce.main.repository.models.remotedata.Products;
import com.example.ecommerce.main.ui.ItemPage;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class NewArrivals  extends RecyclerView.Adapter<NewArrivals.MyViewHolder> {
    Context ctx;
    List<Products> newArrivalsList;

    public NewArrivals(Context ctx, List<Products> newArrivalsList) {
        this.ctx = ctx;
        this.newArrivalsList = newArrivalsList;

    }

    @NonNull
    @Override
    public NewArrivals.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.newarrivalcell,parent,false);

       NewArrivals.MyViewHolder viewHolder = new NewArrivals.MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivals.MyViewHolder holder, int position) {


        holder.namefilter.setText(newArrivalsList.get(position).getTitle());
        holder.pricefilter.setText(String.valueOf(newArrivalsList.get(position).getPrice()) + " $");
        Glide.with(ctx).load(newArrivalsList.get(position).getImages().get(0))
                .into(holder.imagefilter);
        int idchosen = newArrivalsList.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category cat =newArrivalsList.get(holder.getAdapterPosition()).getCategory();
                ArrayList<String> imageslist = newArrivalsList.get(holder.getAdapterPosition()).getImages();
                Log.i("id", Integer.toString(idchosen) + "target");
                Intent intent = new Intent(ctx, ItemPage.class);
                intent.putExtra("id2", idchosen);


                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newArrivalsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagefilter;
        public TextView namefilter;

        public TextView pricefilter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagefilter = itemView.findViewById(R.id.choosenfilterpic);
            namefilter = itemView.findViewById(R.id.choosenfiltername);

            pricefilter = itemView.findViewById(R.id.specialpricefilter);

        }
    }
}
