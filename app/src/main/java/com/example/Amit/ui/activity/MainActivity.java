package com.example.Amit.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.Amit.R;
import com.example.Amit.helper.TokenManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    TokenManager tokenManager;
    BottomNavigationView bottomNavigationView;
    NavController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenManager = new TokenManager(this);
setUpNavigation_with_button_nav();
       String s= tokenManager.getToken();
        Log.d("ddddddddddddd", "onCreate: "+s);

    }




    protected void setUpNavigation_with_button_nav()
    {
        bottomNavigationView=findViewById(R.id.bottom_nav);
        controller=Navigation.findNavController(this,R.id.Host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,controller);
    }
}