package com.medisook.app;

import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_nickname;
    private EditText et_password;
    private EditText warn_nk;
    private EditText warn_pw;
    private Button join_btn;
    private Button check_nk;
    private Context mContext;
    boolean nk_result=false;
    boolean nk_check=false;
    boolean pw_result = false;
    MenuFragmentSearch mf=new MenuFragmentSearch();
    String nk;
    String nk_final;
    String pw;
    String pw_final;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        mContext = this;

        warn_nk = (EditText) findViewById(R.id.warn_nk);
        warn_pw = (EditText) findViewById(R.id.warn_pw);
        et_password = (EditText) findViewById(R.id.password);
        et_password.setOnClickListener(this);
        et_nickname = (EditText) findViewById(R.id.nickname);
        et_nickname.setOnClickListener(this);
        join_btn = (Button) findViewById(R.id.join_btn);
        join_btn.setOnClickListener(this);
        check_nk = (Button) findViewById(R.id.check_nk);
        check_nk.setOnClickListener(this);



        et_nickname.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                nk =  et_nickname.getText().toString();
                check_nk.setBackgroundColor(Color.parseColor("#445E86"));
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
                            check_nk.setBackground(check_nk.getBackground());

                            if(nk.length() >= 11 || nk.length()==0) {
                                et_nickname.setText(null);
                                warn_nk.setText("!잘못된 형식입니다.");
                                nk_result= false;
                            }
                            else{
                                warn_nk.setText(null);
                                nk_result= true;
                            }
                            Log.d("회원가입", "result: " + nk_result);
                            Log.d("회원가입", "닉네임: " + nk);
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            //getWindow().sethideSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        return true;
                    case KeyEvent.KEYCODE_DEL:
                }
                return false;
            }
        });

        et_password.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                pw = et_password.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
                            if(Pattern.matches("^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,20}$", pw)) {
                                warn_pw.setText(null);
                                pw_result = true;
                            }
                            else {
                                et_password.setText(null);
                                warn_pw.setText("!잘못된 형식입니다.");
                                pw_result = false;
                            }
                            Log.d("회원가입", "패스워드: " + pw_result);
                            Log.d("회원가입","패스워드: " + pw);
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        } return true;
                    case KeyEvent.KEYCODE_DEL:
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        //Log.v("회원가입", "테스트: " + nk_result);
        //Log.v("회원가입", "테스트: " + pw_result);
        switch (v.getId()){
            case R.id.join_btn:
                Log.v("회원가입", "테스트- " + "닉네임형식: " + nk_result + "비번형식: " + pw_result + "닉네임중복확인: "+ nk_check);
                if(nk_result == true && pw_result == true && nk_check==true){//중복확인 여부 확인 후 회원가입 성공시키기.
                    nk_final = nk;
                    pw_final = pw;
                    mf = new MenuFragmentSearch();
                    mf.getNickname(nk_final, pw_final);
                    MenuFragmentSearch.InsertData insert = mf.new InsertData();
                    insert.execute("http://" + IP_ADDRESS + "/join.php", "0");
                    Log.d("닉네임", "nk final :  "+ nk_final);
                    Toast.makeText(this.getApplicationContext(),"회원가입 성공!", Toast.LENGTH_SHORT).show();
                    Intent intentLoginActivity =
                            new Intent(JoinActivity.this, LoginActivity.class);
                    startActivity(intentLoginActivity);

                    Log.d("닉네임", "어디까지 실행되나");
                }
                else if(nk_check==false){
                    Toast.makeText(this.getApplicationContext(),"닉네임 중복확인을 해주세요.", Toast.LENGTH_SHORT).show();
                }
//                else{
//                    Toast.makeText(this.getApplicationContext(),"회원가입을 실패했습니다.\n다시 시도하십시오.", Toast.LENGTH_SHORT).show();
//                    et_password.setText(null);
//                    et_nickname.setText(null);
//                }
                break;
            case R.id.check_nk:
                nk_final = nk;
                mf = new MenuFragmentSearch();
                mf.getNickname(nk_final, null);
                MenuFragmentSearch.ReadData read = mf.new ReadData();
                read.execute("http://" + IP_ADDRESS + "/login.php", "3");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("로그인", mf.getresult());

                        if(mf.getresult().contains("TRUE")){
                            nk_check = false;
                            Toast.makeText(JoinActivity.this, "중복닉네임입니다.", Toast.LENGTH_SHORT).show();
                            Log.d("로그인", "중복닉네임입니다");
                            et_nickname.setText(null);
                        }
                        else if(mf.getresult().contains("FALSE")){
                            nk_check = true;
                            check_nk.setBackgroundColor(Color.parseColor("#D3D3D3"));
//                            check_nk.setOnClickListener(new View.OnClickListener(){
//                                @Override
//                                public void onClick(View v){
//                                    check_nk.setBackgroundColor(Color.parseColor("#D3D3D3"));
//                                }
//                            });
                            Toast.makeText(JoinActivity.this, "사용가능한 닉네임입니다.", Toast.LENGTH_SHORT).show();
                            Log.d("로그인", "사용가능한 닉네임입니다.");
                            nk_final = nk;
                        }
                        Log.d("회원가입", "중복확인 " + nk_check + nk_final);
                    }

                }, 300);


                //닉네임 중복확인 부분
        }

    }
}
