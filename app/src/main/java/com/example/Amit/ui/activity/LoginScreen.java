package com.example.Amit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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


public class LoginScreen extends AppCompatActivity {
    EditText name_edit, email_edit, pass_edit;
    Button signin_btn;
    LinearLayout layout;
    ProgressBar progressBar;
    TextView d;
     CheckBox saveLoginCheckBox;
    //token manager
    private Boolean saveLogin;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        initView();
        String s= tokenManager.getToken();
        String s1= tokenManager.getUsername();
        String s2= tokenManager.getPassword();

        if (s!=null || s1!=null && s2!=null) {
            email_edit.setText(s1);
            pass_edit.setText(s2);
            Bundle bundle= new Bundle();
            bundle.putString("username",s1);
            bundle.putString("password",s2);

        }


        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SignUpScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        email_edit = findViewById(R.id.Username);
        pass_edit = findViewById(R.id.password);
        signin_btn = findViewById(R.id.button_SignIn);
        progressBar=findViewById(R.id.prog);
        layout=findViewById(R.id.parent_layout);
d=findViewById(R.id.dont);
        tokenManager=new TokenManager(this);
    }


    private void signUp() {



        String email = email_edit.getText().toString().trim();
        String pass = pass_edit.getText().toString().trim();

        if ( email.isEmpty() || pass.isEmpty())
            Toast.makeText(this, "check your data", Toast.LENGTH_SHORT).show();
        else {

            progressBar.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);

            Map<String, String> user = new HashMap();
            user.put("email", email);
            user.put("password", pass);

            ApiManager.getUserService()
                    .userLogin(user)
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                            progressBar.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);

                            if (response.isSuccessful()) {
                                tokenManager.saveToken(response.body().getToken());
                                Log.d("ddddddd", "onResponse: " + response.body().getToken());

                                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                                startActivity(intent);


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