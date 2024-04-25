package com.example.nguyentanlinh_2122110398.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyentanlinh_2122110398.MainActivity;
import com.example.nguyentanlinh_2122110398.R;
import com.example.nguyentanlinh_2122110398.api.ApiService;
import com.example.nguyentanlinh_2122110398.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userName = findViewById(R.id.username);
        EditText passwordText = findViewById(R.id.passwordText);
        Button btnSignIn = findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String password = passwordText.getText().toString();

                if(username == null || username.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                } else if (password == null || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    passwordText.requestFocus();
                    return;
                }else {
                    ApiService.apiService.login(username,password).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();
                            if(loginResponse != null){
                                // Sử dụng token ở đây để tiếp tục đăng nhập
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Connection errors!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        finish();
    }
}
