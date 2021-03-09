package com.example.Amit.ui.fragment.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Amit.R;
import com.example.Amit.data.model.products.Product;
import com.example.Amit.helper.TokenManager;
import com.example.Amit.ui.activity.LoginScreen;
import com.example.Amit.ui.activity.SignUpScreen;


public class ProfileFragment extends Fragment {

    Button log_out;
EditText username,password;
TokenManager tokenManager;
String s1,s2;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            s1=getArguments().getString("username");
            s2=getArguments().getString("password");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);


        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), LoginScreen.class);
                startActivity(in);
                getActivity().finishAffinity();
            }
        });
    }

    protected void initview(View v){
        log_out = v.findViewById(R.id.button_logout);
        username=v.findViewById(R.id.username);
        password=v.findViewById(R.id.password);

    }


}