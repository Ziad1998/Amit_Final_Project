package com.example.Amit.ui.adapter.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Amit.R;
import com.example.Amit.data.model.cart.ProductsItem;

import java.util.ArrayList;
import java.util.List;

public class cartadapter extends RecyclerView.Adapter<cartadapter.contactHolder> {


    private List<ProductsItem> modelList = new ArrayList<>();
    private Context context;
CartClickLiseter  cartClickLiseter;
    public cartadapter(Context context, CartClickLiseter cartClickLiseter) {
        this.context = context;
        this.cartClickLiseter=cartClickLiseter;

    }

    public void setModelList(List<ProductsItem> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public cartadapter.contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new contactHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull cartadapter.contactHolder holder, int position) {

        ProductsItem m = modelList.get(position);
        if (m != null) {

            Glide.with(context).load(m.getProduct().getAvatar()).into(holder.image);
            holder.name.setText(m.getProduct().getName());
            holder.title.setText(m.getProduct().getTitle());
            holder.price.setText(String.valueOf(m.getProduct().getPrice()) + " EGP");
            holder.name.setText(m.getProduct().getName());
            holder.cartamount.setText(Double.toString(m.getAmount()));

        }

        holder.imagebuttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              int count=  Integer.parseInt(holder.cartamount.getText().toString());
                count++;
                cartClickLiseter.addProduct(m.getProduct(),count);


            }

        });
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class contactHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, name, desc, price, cartamount;
        ImageButton imagebuttonadd;

        public contactHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image_cart);
            name = itemView.findViewById(R.id.product_name_cart);
            title = itemView.findViewById(R.id.product_title_cart);
            price = itemView.findViewById(R.id.product_price_cart);
            imagebuttonadd = itemView.findViewById(R.id.increment_btn);
            cartamount = itemView.findViewById(R.id.product_cart_amount);
        }
    }

}
