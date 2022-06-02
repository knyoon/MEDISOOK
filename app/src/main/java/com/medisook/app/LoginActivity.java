package com.medisook.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import retrofit2.http.PUT;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button joinbtn = (Button) findViewById(R.id.join_btn) ;
        joinbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentJoinActivity =
                        new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intentJoinActivity);
            }
        });

        Button loginbtn = (Button) findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intentMainActivity =
                        new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intentMainActivity);
            }
        });
    }
}
