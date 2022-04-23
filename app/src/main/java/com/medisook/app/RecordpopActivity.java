package com.medisook.app;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecordpopActivity extends Activity {
    TextView TextView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.record_pop);
        //getWindow().setGravity(Gravity.CENTER);

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
//        String yyear = String.valueOf(year);
//        char[] year_2 = yyear.toCharArray();
//        Log.d("인탠드", String.valueOf(year_2));
//        //String sliceyyear =String.valueOf(year).substring(String.valueOf(year).length()-2, String.valueOf(year).length());
//        //Log.d("인탠드", "여기엔 있니" + sliceyyear);
//        Log.d("인탠드", "여기엔 있니" + String.valueOf(year));
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



//        TextView_get = findViewById(R.id.to_date);
//        Intent intent = new Intent(this.getIntent());
//        Log.d("인탠드", "여기로 안오나?");
//        int year = intent.getIntExtra("mYear", 0);
//        Log.d("인탠드", String.valueOf(year));
//        TextView_get.setText(String.valueOf(year));


        //Bundle bundle = intent.getExtras(); //Extra들을 가져옴
//        String year = bundle.getString("mYear");   //가져온 Extars 중에서 꺼내기
//        String month = bundle.getString("mMonth");
//
//        TextView_get.setText(String.valueOf(year + "/" + month));
    }
}
