package com.example.Amit.data.source.api;

import com.example.Amit.data.model.AddToCartResponse;
import com.example.Amit.data.model.products.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("api/products")
    Call<Response> getproduct();

    @PUT("api/user/products/{id}")
    Call<AddToCartResponse> addToCart(
            @Path("id") int id,
            @Header("Authorization")String token,
            @Query("amount") int amount
    );



}
