package com.medisook.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FromdatePickerActivity extends AppCompatActivity {
    private int fYear =0, fMonth=0, fDay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fromdate_picker);

        Calendar calendar = new GregorianCalendar();
        fYear = calendar.get(Calendar.YEAR);
        fMonth = calendar.get(Calendar.MONTH);
        fDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePicker datePicker = findViewById(R.id.fromdate_picker);
        datePicker.init(fYear, fMonth, fDay,fOnDateChangedListener);
    }

    public void mOnClick(View v){
        Intent from_ymd = new Intent(this, RecordpopActivity.class);
        from_ymd.putExtra("fYear",fYear);
        from_ymd.putExtra("fMonth", fMonth);
        from_ymd.putExtra("fDay", fDay);
        setResult(RESULT_OK, from_ymd);
        Log.d("태그","from_ymd 넘기기" +fYear + fMonth +fDay);
        getIntent().addFlags(from_ymd.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(from_ymd);
        finish();
    }

    DatePicker.OnDateChangedListener fOnDateChangedListener = new DatePicker.OnDateChangedListener(){

        @Override
        public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {
            fYear = yy;
            fMonth = mm;
            fDay = dd;
        }
    };
}
