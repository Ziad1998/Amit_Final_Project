package com.example.Amit.data.source.api;

import com.example.Amit.data.model.UserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

       @POST("api/register")
       Call<UserResponse>userRegister(@Body Map<String ,String> user);
       @POST("api/login")
       Call<UserResponse>userLogin(@Body Map<String ,String> user);


}
