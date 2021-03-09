package com.example.Amit.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private SharedPreferences shared;

    public TokenManager(Context context) {
        shared = context.getSharedPreferences("token", Context.MODE_PRIVATE);
    }


    public void saveToken(String token) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("token", token);

        editor.apply();
    }
    public void saveUsername(String username) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("username", username);

        editor.apply();
    }
    public void savePassword(String password) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("password", password);

        editor.apply();
    }
    public String getToken() {
      return shared.getString("token",null);
    }
    public String getUsername() {
        String restoredUser = shared.getString("username", null);


        return  restoredUser;

    }
    public String getPassword() {
        String restoredPassword = shared.getString("password", null);


        return  restoredPassword;

    }


}
