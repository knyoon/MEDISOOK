package com.medisook.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity_save extends AppCompatActivity {
    Dialog red_popup;
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

        Dialog red_popup = new Dialog(SearchActivity_save.this);
        Button red_filter_btn = (Button) findViewById(R.id.red_filter_btn);
        red_popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        red_popup.setContentView(R.layout.redpop);
        red_filter_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                red_popup.show();
                red_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button popup_ok_btn = (Button) red_popup.findViewById(R.id.popup_ok_btn);
                popup_ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        red_popup.dismiss();
                    }
                });
            }
        });
    }
//        버튼 구현
//        final Button filter_btn1 = (Button) findViewById(R.id.red_filter_btn);
////        final Button filter_btn2 = (Button) findViewById(R.id.green_filter_btn);
////        final Button filter_btn3 = (Button) findViewById(R.id.yellow_filter_btn);
//        filter_btn1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SearchActivity.this, RedpopActivity.class);
//                startActivity(intent);
//            }
//        });

}