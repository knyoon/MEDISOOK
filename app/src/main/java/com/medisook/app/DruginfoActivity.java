package com.medisook.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DruginfoActivity extends AppCompatActivity implements View.OnClickListener{
//    Calendar myCalendar = Calendar.getInstance();
//    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, month);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_info);

        TextView Medi = findViewById(R.id.medisook); // 변수 선언
        String content = Medi.getText().toString(); //텍스트 가져옴
        SpannableString spannableString = new SpannableString(content); //객체생성
        String word = "MEDI";
        int start = content.indexOf(word);
        int end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#68B981")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Medi.setText(spannableString);
        //버튼
        final Button record_pop_btn = (Button) findViewById(R.id.record_pop_btn);
        record_pop_btn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        //LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()){
            case R.id.record_pop_btn:
                CustomDialog_record dialog = new CustomDialog_record(this);
                CustomDialog_record.Builder dialog_bulider = new CustomDialog_record.Builder(this);
                dialog.setDialogListener(new CustomDialog_record.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                    }
                });
                dialog.show();
                break;
//            case R.id.to_date:
//                new DatePickerDialog(this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
//    private void updateLabel(){
//        String myFormat = "yyyy/MM/dd";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
//        EditText et_date = (EditText) findViewById(R.id.to_date);
//    }
//
//    public void mOnClick(View view) {
//        Intent back = new Intent(this, DruginfoActivity.class);
//        getIntent().addFlags(back.FLAG_ACTIVITY_CLEAR_TOP);
//        //startActivity(back);
//        finish();
//    }
}