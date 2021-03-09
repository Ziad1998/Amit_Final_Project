package com.example.Amit.ui.adapter.product;

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
import com.example.Amit.data.model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class recycler_adapter  extends RecyclerView.Adapter<recycler_adapter.contactHolder> {

    private List<Product> modelList =new ArrayList<>();
  private   Context context;
  serviceProduct listener;
    public recycler_adapter(Context context , serviceProduct listner)
    {
        this.context=context;
        this.listener=listner;
    }
    public void setModelList(List<Product> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged(); //5od balk y adapter fe data gayalk gdeda 23ml refressh
          }


    @NonNull
    @Override
    public contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_layout,parent,false);
        return new contactHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {

        Product m=  modelList.get(position);
        if(m!=null)
        {

            Glide.with(context).load(m.getAvatar()).into(holder.image);
            holder.name.setText(m.getTitle());
            holder.desc.setText( m.getName());
            holder.price.setText(String.valueOf(m.getPrice())+" EGP");


            String substring = holder.desc.getText().toString().substring(0,20);
            holder.desc.setText(String.valueOf(substring));
            String substring2 = holder.name.getText().toString().substring(0,14);
            holder.name.setText(String.valueOf(substring2));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showDetails(m);
            }
        });

        holder.imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.AddToCart(m);
            }
        });
    }



    @Override
    public int getItemCount() {
        return modelList.size();
    }
    class contactHolder extends RecyclerView.ViewHolder {

        ImageView image ;
        TextView name , desc , price;
        ImageButton imagebutton;

        public contactHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.product_image);
            name=itemView.findViewById(R.id.product_name);
            desc=itemView.findViewById(R.id.product_desc);
            price=itemView.findViewById(R.id.product_price);
imagebutton=itemView.findViewById(R.id.add_cart_btn);

        }
    }

}
