package com.medisook.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        TextView Medi = findViewById(R.id.medisook); // 변수 선언
        String content = Medi.getText().toString(); //텍스트 가져옴
        SpannableString spannableString = new SpannableString(content); //객체생성
        String word = "MEDI";
        int start = content.indexOf(word);
        int end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#68B981")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Medi.setText(spannableString);
//        버튼 구현
        final Button filter_btn1 = (Button) findViewById(R.id.red_filter_btn);
//        final Button filter_btn2 = (Button) findViewById(R.id.green_filter_btn);
//        final Button filter_btn3 = (Button) findViewById(R.id.yellow_filter_btn);

        filter_btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, RedpopActivity.class);
                startActivity(intent);
            }
        });
    }
}