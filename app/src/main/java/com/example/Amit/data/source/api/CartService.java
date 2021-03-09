package com.example.Amit.data.source.api;

import com.example.Amit.data.model.cart.Cart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CartService {
    @GET("api/user/products")
    Call<Cart> cartdetails(@Header("Authorization") String id);
}
