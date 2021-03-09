package com.example.Amit.data.repisotory;

import androidx.lifecycle.MutableLiveData;

import com.example.Amit.data.model.cart.Cart;
import com.example.Amit.data.model.cart.ProductsItem;
import com.example.Amit.data.source.api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cartRepisotory {
    private MutableLiveData<List<ProductsItem>> CartLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageCartLiveData = new MutableLiveData<>();


    public MutableLiveData<List<ProductsItem>> getCartLiveData() {
        return CartLiveData;
    }

    public MutableLiveData<String> getMessageCartLiveData() {
        return messageCartLiveData;
    }

    public void Cartproducts(String token) {

            ApiManager.cart().cartdetails("Bearer "+token).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
        if(response.isSuccessful())
            CartLiveData.setValue(response.body().getProducts());
        else
            messageCartLiveData.setValue((response.message()));

    }


    @Override
    public void onFailure(Call<Cart> call, Throwable t) {
        messageCartLiveData.setValue(t.getLocalizedMessage());
    }
});

    }
}