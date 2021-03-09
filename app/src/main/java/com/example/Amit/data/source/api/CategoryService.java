package com.example.Amit.data.source.api;

import com.example.Amit.data.model.category.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("api/categories")
    Call<CategoryResponse> getCategory();

}
