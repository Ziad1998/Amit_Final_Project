package com.example.Amit.ui.adapter.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Amit.R;
import com.example.Amit.data.model.category.CategoriesItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Categories_adapter extends RecyclerView.Adapter<Categories_adapter.contactHolder> {
    private Context context;

    public Categories_adapter(Context context) {
        this.context = context;
    }

    private List<CategoriesItem> catList =new ArrayList<>();

    public void setModelList(List<CategoriesItem> catList) {
        this.catList = catList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public contactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_recycle,parent,false);
        return new contactHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull contactHolder holder, int position) {
        CategoriesItem m=  catList.get(position);
        if(m!=null)
        {

            Picasso.with(context).load(m.getAvatar()).into(holder.image);
            holder.name.setText(m.getName());




        }

    }


    @Override
    public int getItemCount() {
        return catList.size();
    }

    class contactHolder extends RecyclerView.ViewHolder {

        ImageView image ;
        TextView name;

        public contactHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.category_image);
            name=itemView.findViewById(R.id.category_name);



        }
    }
}
