package com.example.sampleapp.Basics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.sampleapp.R;
import com.example.sampleapp.Utils.UserProfileManager;

public class LoginScreen extends AppCompatActivity {
    private RelativeLayout Login;
    private AppCompatEditText username,password;
    private TextView to_register;
    Activity CURRENT_ACTIVITY = LoginScreen.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Initialize();

        to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CURRENT_ACTIVITY,RegisterScreen.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("1")){
                    //Admin
                    username.setText("");
                    UserProfileManager.saveUser(CURRENT_ACTIVITY,"1");
                }else if (username.getText().toString().equals("2")){
                    //Manager
                    username.setText("");
                    UserProfileManager.saveUser(CURRENT_ACTIVITY,"2");
                }else if (username.getText().toString().equals("3")){
                    //Technician
                    username.setText("");
                    UserProfileManager.saveUser(CURRENT_ACTIVITY,"3");
                }else {
                    //Normal
                    username.setText("");
                    UserProfileManager.saveUser(CURRENT_ACTIVITY,"4");
                }
                startActivity(new Intent(CURRENT_ACTIVITY,HomeActivity.class));
            }
        });

    }

    @SuppressLint("WrongViewCast")
    private void Initialize() {
        to_register=(TextView)findViewById(R.id.signup_text);
        username=(AppCompatEditText) findViewById(R.id.username);
        password=(AppCompatEditText) findViewById(R.id.password);
        Login=(RelativeLayout)findViewById(R.id.login_btn);
    }
}
