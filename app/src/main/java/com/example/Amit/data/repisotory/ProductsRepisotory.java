package com.example.Amit.data.repisotory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.Amit.data.model.products.Product;
import com.example.Amit.data.model.products.Response;
import com.example.Amit.data.source.api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductsRepisotory {

    private MutableLiveData<List<Product>> productsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Product>> getProductsMutableLiveData() {
        return productsMutableLiveData;
    }

    public MutableLiveData<String> getMessageMutableLiveData() {
        return messageMutableLiveData;
    }

    public void getAllProducts()
  {
      ApiManager.g().getproduct().enqueue(new Callback<Response>() {
          @Override
          public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


              if (response.isSuccessful()) {
                  Log.d("ddddd", "onResponse: "+response.body().getProducts());
                  productsMutableLiveData.setValue(response.body().getProducts());
              }
          }

          @Override
          public void onFailure(Call<Response> call, Throwable t) {
              Log.d("ddddd", "onResponse: "+t.getLocalizedMessage());
              messageMutableLiveData.setValue(t.getLocalizedMessage());

          }
      });
  }







}
