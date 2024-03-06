package com.example.ecommerce.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.main.repository.models.localdata.Cart;
import com.example.ecommerce.main.ui.Checkout;
import com.example.ecommerce.main.ui.Mainscreen;
import com.example.ecommerce.main.ui.Mycart;
import com.example.ecommerce.main.ui.SuccessLogin;
import com.example.ecommerce.main.util.Helper;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {
    Context ctx;
    ArrayList<Cart> CartList;
    public static float totalprice;
    public static int totalquantity;
    public CheckoutAdapter(Context ctx, ArrayList<Cart> CartList) {

        this.ctx = ctx;
        this.CartList = CartList;
        updateprice();
    }

    @NonNull
    @Override
    public CheckoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.cartcell,parent,false);
        CheckoutAdapter.MyViewHolder viewHolder = new CheckoutAdapter.MyViewHolder(recyclerView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.MyViewHolder holder, int position) {
        float price= CartList.get(position).getPrice();

        int quantity =CartList.get(position).getQuantity();
        holder.cartitemprice.setText(String.valueOf(price*quantity)+"$");
        holder.CartItemQuantity.setText(String.valueOf(quantity));
        holder.carttitle.setText(CartList.get(position).getTitle());

        Glide.with(ctx).load(CartList.get(position).getImages()).into(holder.cartimage);
        updateprice();

        holder.itemremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mainscreen.dbHelper.deleteItem(String.valueOf(CartList.get(holder.getAdapterPosition()).getId()));
                CartList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                updateprice();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =CartList.get(holder.getAdapterPosition()).getQuantity();
                quantity=quantity+1;
                float price= CartList.get(holder.getAdapterPosition()).getPrice();
                holder.cartitemprice.setText(String.valueOf(price*quantity)+"$");
                holder.CartItemQuantity.setText(String.valueOf(quantity));
                CartList.get(holder.getAdapterPosition()).setQuantity(quantity);

                updatedb(Integer.toString( CartList.get(holder.getAdapterPosition()).getId()),
                        CartList.get(holder.getAdapterPosition()).getTitle(),
                        price,
                        CartList.get(holder.getAdapterPosition()).getDescription(),
                        CartList.get(holder.getAdapterPosition()).getCategory(),
                        CartList.get(holder.getAdapterPosition()).getImages(),
                        Integer.toString( CartList.get(holder.getAdapterPosition()).getQuantity())


                );
                notifyItemChanged(holder.getAdapterPosition());
                updateprice();

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CartList.get(holder.getAdapterPosition()).getQuantity()>1)
                {
                    int quantity =CartList.get(holder.getAdapterPosition()).getQuantity();
                    quantity=quantity-1;
                    float price= CartList.get(holder.getAdapterPosition()).getPrice();
                    holder.cartitemprice.setText(String.valueOf(price*quantity)+"$");
                    holder.CartItemQuantity.setText(String.valueOf(quantity));
                    CartList.get(holder.getAdapterPosition()).setQuantity(quantity);
                    updatedb(Integer.toString( CartList.get(holder.getAdapterPosition()).getId()),
                            CartList.get(holder.getAdapterPosition()).getTitle(),
                            price,
                            CartList.get(holder.getAdapterPosition()).getDescription(),
                            CartList.get(holder.getAdapterPosition()).getCategory(),
                            CartList.get(holder.getAdapterPosition()).getImages(),
                            Integer.toString( CartList.get(holder.getAdapterPosition()).getQuantity())


                    );
                    notifyItemChanged(holder.getAdapterPosition());
                    updateprice();

                }
            }
        });

    }
    public void updatedb(String id, String title,float price,String description, String category,String images,String quantity)
    {
        String userid=Integer.toString( Helper.Activeuser.getId());
        String email=Helper.Activeuser.getEmail();

        Mainscreen.dbHelper.UpdateItem(userid,email,id,title,price,description,category,images,quantity);
    }
    public  void updateprice()
    {
        totalprice=0;
        totalquantity=0;
        for(int i=0;i<CartList.size();i++)
        {
            totalprice=totalprice + CartList.get(i).getPrice()*CartList.get(i).getQuantity();
            totalquantity+=CartList.get(i).getQuantity();
        }
        Helper.setTotalprice(totalprice);
        Helper.setTotalquantity(totalquantity);
        Checkout.pricesetter();
    }


    @Override
    public int getItemCount() {
        return CartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView cartimage,itemremove,add,minus;
        TextView carttitle,cartitemprice,CartItemQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartimage=itemView.findViewById(R.id.cartimage);
            this.carttitle=itemView.findViewById(R.id.carttitle);
            this.CartItemQuantity=itemView.findViewById(R.id.CartItemQuantity);
            this.cartitemprice=itemView.findViewById(R.id.cartitemprice);
            this.itemremove=itemView.findViewById(R.id.itemremove);
            this.add=itemView.findViewById(R.id.Addbtncart);
            this.minus=itemView.findViewById(R.id.Minusbtncart);
        }
    }
}
