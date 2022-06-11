package com.medisook.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nickname;
    private EditText password;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        nickname = (EditText) findViewById(R.id.nickname);
        nickname.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String nk = nickname.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
                            Log.d("회원가입", "닉네임: " + nk);
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            //getWindow().sethideSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        } return true;
                    case KeyEvent.KEYCODE_DEL:
                }
                return false;
            }
        });

        password = (EditText) findViewById(R.id.password);
        password.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String pw = password.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
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

        boolean result=false;
        String nk = nickname.getText().toString();
        Log.v("회원가입", nk + " : 입력한 닉네임");
        switch (v.getId()) {
            case R.id.nickname:
                if(nk.length()>10){
                    Toast.makeText(JoinActivity.this, "닉네임은 10글자 이하로 입력해주세요",
                            Toast.LENGTH_LONG).show();
                    result= false;
                }
                else {
                    result=true;
                }

//            case R.id.password:

        }
    }
}
