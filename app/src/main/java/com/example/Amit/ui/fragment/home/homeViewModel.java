package com.example.Amit.ui.fragment.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Amit.data.model.AddToCartResponse;
import com.example.Amit.data.model.products.Product;
import com.example.Amit.data.repisotory.ProductsRepisotory;
import com.example.Amit.data.source.api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class homeViewModel  extends ViewModel {

    private LiveData<List<Product>>ProductLiveData;
    private LiveData<String> messageLiveData ;

    public LiveData<List<Product>> getProductLiveData() {
        return ProductLiveData;
    }

    public LiveData<String> getMessageLiveData() {
        return messageLiveData;
    }
    private MutableLiveData<String> addCartResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String>messageAddCartLiveData = new MutableLiveData<>();



    public MutableLiveData<String> getAddCartResponseMutableLiveData() {
        return addCartResponseMutableLiveData;
    }

    public MutableLiveData<String> getMessageAddCartLiveData() {
        return messageAddCartLiveData;
    }

    public void addProductsToCart(int id, String token, int amount) {

        ApiManager.g().addToCart(id, "Bearer " + token, amount).enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, retrofit2.Response<AddToCartResponse> response) {
                if (response.isSuccessful()) {

                    addCartResponseMutableLiveData.setValue(response.body().getMessage());

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

    private ProductsRepisotory reprository = new ProductsRepisotory();

    public void getProducts(){
        reprository.getAllProducts();
        ProductLiveData=reprository.getProductsMutableLiveData();
        messageLiveData = reprository.getMessageMutableLiveData();
    }

}
