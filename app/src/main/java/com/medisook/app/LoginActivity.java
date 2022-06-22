package com.medisook.app;

import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText et_nk;
    EditText et_pw;
    String nk;
    String pw;
    MenuFragmentSearch mf=new MenuFragmentSearch();
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
                et_nk  = (EditText) findViewById(R.id.login_id);
                nk = et_nk.getText().toString();
                et_pw  = (EditText) findViewById(R.id.login_pw);
                pw = et_pw.getText().toString();
                mf.getNickname(nk, pw);
                Intent intentMainActivity =
                        new Intent(LoginActivity.this, MainMenuActivity.class);
                intentMainActivity.putExtra("nickname", nk);
                Log.d("닉네임", "로그인화면 : "+ nk + pw);
                MenuFragmentSearch.ReadData read = mf.new ReadData();
                read.execute("http://" + IP_ADDRESS + "/login.php", "3");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("로그인", mf.getresult() + "로그");
                        if(mf.getresult().contains("TRUE")){//세영, aaaa11111
                            Log.d("로그인", "닉네임 비밀번호 일치");
                            Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                            startActivity(intentMainActivity);//로그인에 성공해야 검색화면으로 넘어감
                        }
                        else if(mf.getresult().contains("FALSE")){
                            Log.d("로그인", "닉네임 비밀번호 불일치");
                            Toast.makeText(LoginActivity.this, "닉네임과 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, 2000);
            }
        });
    }
}
