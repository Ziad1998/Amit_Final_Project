package com.example.Amit.ui.fragment.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Amit.R;
import com.example.Amit.data.model.cart.ProductsItem;
import com.example.Amit.data.model.products.Product;
import com.example.Amit.helper.TokenManager;
import com.example.Amit.ui.adapter.cart.CartClickLiseter;
import com.example.Amit.ui.adapter.cart.cartadapter;

import java.util.List;


public class CartFragment extends Fragment implements CartClickLiseter {


    public CartFragment() {
        // Required empty public constructor
    }
    RecyclerView cartRecycler;
     cartadapter adapter;
     CartViewModel cartViewModel;
    RecyclerView.LayoutManager layoutManager;
    NavController navController;
    int amount =1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
    protected void intiRecycler(View v){

        cartRecycler=v.findViewById(R.id.cart_recyclerView);
        adapter=new cartadapter(getContext(),this);

        layoutManager=new LinearLayoutManager(getContext());
        cartRecycler.setLayoutManager(layoutManager);

        cartRecycler.setAdapter(adapter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiRecycler(view);

        TokenManager token =new TokenManager(getContext());
        String tokenid=token.getToken();
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartViewModel.getCartProducts(tokenid);
        cartViewModel.getCartLiveData().observe(requireActivity(), new Observer<List<ProductsItem>>() {
            @Override
            public void onChanged(List<ProductsItem> productsItems) {
                adapter.setModelList(productsItems);

            }
        });

        cartViewModel.getMessageCartLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getActivity(),""+s,Toast.LENGTH_LONG).show();
            }
        });
        cartViewModel.getAddCartResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(),""+s,Toast.LENGTH_LONG).show();
            }
        });    }


    @Override
    public void addProduct(Product product, int count) {
        TokenManager token =new TokenManager(getContext());
        String tokenid=token.getToken();
        //ApiManager.g().addToCart(product.getId(),"Bearer "+tokenid,count).enqueue(new Callback<AddToCartResponse>() {
         //   @Override
           // public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
             //   if(response.isSuccessful())
             //   {
              //      Toast.makeText(getContext(),""+response.body().getMessage(),Toast.LENGTH_LONG).show();

              //  }
              //  else
               // {
                //    Toast.makeText(getContext(),"error happened"+response.code(),Toast.LENGTH_LONG).show();
               // }
           // }

           // @Override
           // public void onFailure(Call<AddToCartResponse> call, Throwable t) {

           // }
      //  });
        cartViewModel.addToCart(product.getId(),tokenid,count);

    }
}