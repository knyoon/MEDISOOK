package com.medisook.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TodatePickerActivity extends AppCompatActivity {
    private int mYear =0, mMonth=0, mDay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.date_picker);

        Calendar calendar = new GregorianCalendar();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePicker datePicker = findViewById(R.id.date_picker);
        datePicker.init(mYear, mMonth, mDay,mOnDateChangedListener);
    }

    public void mOnClick(View v){
        Intent ymd = new Intent(this, RecordpopActivity.class);
        ymd.putExtra("mYear",mYear);
        ymd.putExtra("mMonth", mMonth);
        ymd.putExtra("mDay", mDay);
        setResult(RESULT_OK, ymd);
        Log.d("태그","to_ymd 넘기기" +mYear + mMonth +mDay);
        getIntent().addFlags(ymd.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(ymd);
        finish();
    }

    DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener(){

        @Override
        public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
            mYear = yy;
            mMonth = mm;
            mDay = dd;
        }
    };
}

