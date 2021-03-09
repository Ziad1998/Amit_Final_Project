package com.example.Amit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Amit.R;
import com.example.Amit.data.source.api.ApiManager;
import com.example.Amit.data.model.UserResponse;
import com.example.Amit.helper.TokenManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends AppCompatActivity {

    EditText name_edit, email_edit, pass_edit;
    Button signUp_btn;
    LinearLayout layout;
    ProgressBar progressBar;
TextView haveAccount;
    //token manager
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        initView();


        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        name_edit = findViewById(R.id.Username_signup);
        email_edit = findViewById(R.id.email_signup);
        pass_edit = findViewById(R.id.password_signup);
        signUp_btn = findViewById(R.id.button_SignUp);
        progressBar=findViewById(R.id.prog);
        haveAccount=findViewById(R.id.have);
        layout=findViewById(R.id.parent_layout);

        tokenManager=new TokenManager(this);
    }


    private void signUp() {
        String name = name_edit.getText().toString().trim();
        String email = email_edit.getText().toString().trim();
        String pass = pass_edit.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || pass.isEmpty())
            Toast.makeText(this, "check your data", Toast.LENGTH_SHORT).show();
        else {

            progressBar.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);

            Map<String, String> user = new HashMap();
            user.put("name", name);
            user.put("email", email);
            user.put("password", pass);

            ApiManager.getUserService()
                    .userRegister(user)
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                            progressBar.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);

                            if (response.isSuccessful()) {
                                tokenManager.saveToken(response.body().getToken());
                                tokenManager.saveUsername(email);
                                tokenManager.savePassword(pass);



                                Log.d("ddddddd", "onResponse: " + response.body().getToken());

                                Intent intent = new Intent(SignUpScreen.this, MainActivity.class);
                                startActivity(intent);
                                finish();


                            } else {
                                Log.d("ddddddd", "onResponse: " + response.code());
                            }

                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                            progressBar.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);

                            Log.d("ddddddddd", "onFailure: " + t.getLocalizedMessage());

                        }
                    });


        }
    }


}