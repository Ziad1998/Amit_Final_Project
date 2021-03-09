package com.example.Amit.data.source.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static Retrofit retrofit;
    private static final String BASE_URL="http://retail.amit-learning.com/";

    private static Retrofit getInstance() {

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


    public static UserService  getUserService(){
       return getInstance().create(UserService.class);
    }
    public static ProductService g(){
        return getInstance().create(ProductService.class);
    }
    public static CategoryService category(){
        return getInstance().create(CategoryService.class);
    }
    public static CartService cart(){
        return getInstance().create(CartService.class);
    }





}
