package com.example.Amit.ui.fragment.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Amit.data.model.AddToCartResponse;
import com.example.Amit.data.model.cart.ProductsItem;
import com.example.Amit.data.repisotory.cartRepisotory;
import com.example.Amit.data.source.api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel  extends ViewModel {

    private LiveData<List<ProductsItem>>CartLiveData ;
    private LiveData<String> messageCartLiveData;

    public LiveData<List<ProductsItem>> getCartLiveData() {
        return CartLiveData;
    }

    public LiveData<String> getMessageCartLiveData() {
        return messageCartLiveData;
    }
    private MutableLiveData<String> addResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String>messageAddCartLiveData = new MutableLiveData<>();



    public MutableLiveData<String> getAddCartResponseMutableLiveData() {
        return addResponseMutableLiveData;
    }

    public MutableLiveData<String> getMessageAddCartLiveData() {
        return messageAddCartLiveData;
    }
    private cartRepisotory cartReprository = new cartRepisotory();
    public void addToCart(int id, String token, int count) {

        ApiManager.g().addToCart(id, "Bearer " + token, count).enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                if (response.isSuccessful()) {

                    addResponseMutableLiveData.setValue(response.body().getMessage());

                } else {
                    messageAddCartLiveData.setValue(String.valueOf(response.code()));

                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                messageAddCartLiveData.setValue(t.getLocalizedMessage());

            }
        });
    }
    public void getCartProducts(String token){
        cartReprository.Cartproducts(token);
        CartLiveData = cartReprository.getCartLiveData();
        messageCartLiveData = cartReprository.getMessageCartLiveData();
    }

}
