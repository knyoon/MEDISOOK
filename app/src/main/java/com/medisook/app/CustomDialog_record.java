package com.medisook.app;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomDialog_record extends AlertDialog implements View.OnClickListener{
    TextView TextView_get;
    private EditText to_date;
    private EditText from_date;
    private TextView et_Date;
    private Button okButton;
    private Context context;
    private CustomDialogListener customDialogListener;
    public CustomDialog_record(Context context) {
        super(context);
        this.context = context;
    }
    interface CustomDialogListener{
        void onOkClicked(String text);
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
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
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        et_Date= (TextView) findViewById(R.id.to_date);
        okButton.setOnClickListener(this);
        et_Date.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_ok_btn:
                dismiss();
                break;
            case R.id.to_date:
                Log.v("태그", "context");
                new DatePickerDialog(context, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
    private void updateLabel(){
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date = (TextView) findViewById(R.id.to_date);
    }
//    public void mOnClick(View view) {
//        Intent back = new Intent(this, DruginfoActivity.class);
//        getIntent().addFlags(back.FLAG_ACTIVITY_CLEAR_TOP);
//        //startActivity(back);
//        finish();
//    }
}
