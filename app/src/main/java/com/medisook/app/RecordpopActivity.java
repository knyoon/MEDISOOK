package com.medisook.app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class RecordpopActivity extends Activity {
    TextView TextView_get;
    private EditText to_date;
    private EditText from_date;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.record_pop);

        TextView et_Date = (TextView) findViewById(R.id.to_date);
        et_Date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new DatePickerDialog(RecordpopActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
/*
        final EditText to_date = (EditText) findViewById(R.id.to_date);
        to_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(RecordpopActivity.this, TodatePickerActivity.class), 0);
                onPause();
            }
        });

        final EditText from_date = (EditText) findViewById(R.id.from_date);
        from_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(RecordpopActivity.this, FromdatePickerActivity.class), 0);
                onPause();
            }
        });

        TextView_get = findViewById(R.id.to_date);
        Intent tointent = getIntent();
        int year = tointent.getIntExtra("mYear", 0);
        int month = tointent.getIntExtra("mMonth", 0);
        int date = tointent.getIntExtra("mDate", 0);
        Log.d("인탠드", "todate 넘어왔는지" + String.valueOf(year)+String.valueOf(month)+String.valueOf(date));
        TextView_get.setText(String.valueOf(year)+String.valueOf(month)+String.valueOf(date));

        TextView_get = findViewById(R.id.from_date);
        Intent fromintent = getIntent();
        int from_year = fromintent.getIntExtra("fYear", 0);
        int from_month = fromintent.getIntExtra("fMonth", 0);
        int fr_date = fromintent.getIntExtra("fDate", 0);
        Log.d("인탠드", "formdate 넘어왔는지" + String.valueOf(from_year)+String.valueOf(from_month)+String.valueOf(fr_date));
        TextView_get.setText(String.valueOf(from_year)+String.valueOf(from_month)+String.valueOf(fr_date));
*/
    }

    private void updateLabel(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date = (TextView) findViewById(R.id.to_date);
    }

    public void mOnClick(View view) {
        Intent back = new Intent(this, DruginfoActivity.class);
        getIntent().addFlags(back.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(back);
        finish();
    }
}