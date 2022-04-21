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

public class DruginfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_info);


        Button record_pop_btn = (Button) findViewById(R.id.record_pop_btn);
        record_pop_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RecordpopActivity.class);
                startActivity(intent);
            }
        });

        TextView Medi = findViewById(R.id.medisook); // 변수 선언
        String content = Medi.getText().toString(); //텍스트 가져옴
        SpannableString spannableString = new SpannableString(content); //객체생성

        String word = "MEDI";
        int start = content.indexOf(word);
        int end = start + word.length();

        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#68B981")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Medi.setText(spannableString);

    }
}