package com.example.nguyentanlinh_2122110398.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyentanlinh_2122110398.R;
import com.example.nguyentanlinh_2122110398.api.ApiService;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pass = findViewById(R.id.passwordText);
                String passContent =pass.getText().toString();
                EditText email = findViewById(R.id.emailText);
                String emailContent = email.getText().toString();
                EditText userName = findViewById(R.id.nameText);
                String userNameContent = userName.getText().toString();

                TextView inval = findViewById(R.id.invalidateText);
                TextView enterName = findViewById(R.id.enterName);
                TextView enterPass = findViewById(R.id.enterPass);

                if(userNameContent == null || userNameContent.isEmpty()){
                    userName.requestFocus();
                    enterName.setText("Please enter your username");
                    return;
                } else{
                    enterName.setText("");
                }
                if(emailContent == null || emailContent.isEmpty()){
                    email.requestFocus();
                    inval.setText("Please enter your email");
                    return;
                } else if(!isValidEmail(emailContent)){
                    email.setText("");
                    email.requestFocus();
                    inval.setText("Invalid email address");
                    return;
                } else {
                    inval.setText("");
                }
                if(passContent == null || passContent.isEmpty()) {
                    pass.requestFocus();
                    enterPass.setText("Please enter your password");
                    return;
                }else {
                    enterPass.setText("");
                }

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("Username",userNameContent);
                startActivity(intent);
                finish();
            }
        });

    }

    public void login(View view){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        finish();
    }

//    check is validate email
    public static boolean isValidEmail(CharSequence target){
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}

