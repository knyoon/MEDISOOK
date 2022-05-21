package com.medisook.app;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
    private TextView et_Date1;
    private Button okButton;
    private Context context;
    private EditText et_record;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT < 30)
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        else
//            getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.record_pop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        et_Date= (TextView) findViewById(R.id.to_date);
        et_Date1= (TextView) findViewById(R.id.from_date);

        et_record = (EditText) findViewById(R.id.record);
        et_record.setOnClickListener(this);
        okButton.setOnClickListener(this);
        et_Date.setOnClickListener(this);
        et_Date1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_ok_btn:
                dismiss();
                break;
            case R.id.record:
                showSoftKeyboard();
                break;
            case R.id.to_date:
                Log.v("태그", "to-date 클릭");
                DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Log.d("태그", String.valueOf(year));
                        updateLabel();
                    }
                };
                new DatePickerDialog(context, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.from_date:
                Log.v("태그", "from-date 클릭");
                DatePickerDialog.OnDateSetListener myDatePicker1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        Log.d("태그", String.valueOf(year));
                        updateLabel1();
                    }
                };
                new DatePickerDialog(context, myDatePicker1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
    private void updateLabel(){
        String myFormat = "yy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date = (TextView) findViewById(R.id.to_date);
        et_date.setText(sdf.format(myCalendar.getTime()));
//        TextView et_date1 = (TextView) findViewById(R.id.from_date);
//        et_date1.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel1(){
        String myFormat = "yy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        TextView et_date1 = (TextView) findViewById(R.id.from_date);
        et_date1.setText(sdf.format(myCalendar.getTime()));
    }


    public void showSoftKeyboard() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);

//        et_record.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                et_record.requestFocus();
//                imm.showSoftInput(et_record, 0);
//            }
//        }, 100);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        //imm.showSoftInput(record, InputMethodManager.SHOW_IMPLICIT);

//        InputMethodManager immhide = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
//    public void mOnClick(View view) {
//        Intent back = new Intent(this, DruginfoActivity.class);
//        getIntent().addFlags(back.FLAG_ACTIVITY_CLEAR_TOP);
//        //startActivity(back);
//        finish();
//    }
}
