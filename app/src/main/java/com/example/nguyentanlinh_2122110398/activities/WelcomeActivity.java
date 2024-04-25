package com.example.nguyentanlinh_2122110398.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.nguyentanlinh_2122110398.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void register(View view) {
        startActivity( new Intent(WelcomeActivity.this, RegistrationActivity.class));
    }

    public void login(View view){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}