package com.example.Amit.ui.fragment.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Amit.R;
import com.example.Amit.data.source.api.ApiManager;
import com.example.Amit.data.model.AddToCartResponse;
import com.example.Amit.data.model.products.Product;
import com.example.Amit.helper.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;


public class Details_Fragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public Details_Fragment() {
        // Required empty public constructor
    }

Product product;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
product = (Product) getArguments().getSerializable("product");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_, container, false);
    }
    ImageView imageView;
    TextView textTitle,TextDescribtion,TextName,TextPrice;
Button addcart;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView=view.findViewById(R.id.imageDetails);
        textTitle=view.findViewById(R.id.title_details);
        TextDescribtion=view.findViewById(R.id.describtion_details);
        TextPrice=view.findViewById(R.id.price_Details);
        TextName=view.findViewById(R.id.name_details);
addcart=view.findViewById(R.id.button_add_to_cart);

        Glide.with(getContext()).load(product.getAvatar()).into(imageView);
        textTitle.setText(product.getTitle());
        TextName.setText(product.getName());
        TextPrice.setText(Double.toString(product.getPrice()));
        TextDescribtion.setText(product.getDescription());
addcart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        TokenManager token =new TokenManager(getContext());
        String tokenid=token.getToken();
        ApiManager.g().addToCart(product.getId(),"Bearer "+tokenid,1).enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, retrofit2.Response<AddToCartResponse> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getContext(),"error happened"+response.code(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                Toast.makeText(getContext(),"error happened"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
});
    }


}