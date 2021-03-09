package com.example.Amit.data.repisotory;

import androidx.lifecycle.MutableLiveData;

import com.example.Amit.data.model.category.CategoriesItem;
import com.example.Amit.data.model.category.CategoryResponse;
import com.example.Amit.data.source.api.ApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryRepisotory {
    private MutableLiveData<List<CategoriesItem>> categoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<CategoriesItem>> getcategoryMutableLiveData() {
        return categoryMutableLiveData;
    }

    public MutableLiveData<String> getMessageMutableLiveData() {
        return messageMutableLiveData;
    }

    public void getAllCategories()
    {
        ApiManager.category().getCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                if(response.isSuccessful())
                {
                    categoryMutableLiveData.setValue(response.body().getCategories());

                }
                else
                {
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                messageMutableLiveData.setValue(t.getLocalizedMessage());

            }
        });    }








}
