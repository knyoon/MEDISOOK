package com.medisook.app;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CustomDialog_record extends AlertDialog implements View.OnClickListener{
    private final ArrayList<DrugItem> drugItemArrayList;
    int position;
    TextView TextView_get, textValue;
    private EditText to_date;
    private EditText from_date;
    private TextView drugName;
    private TextView et_Date;
    private TextView et_Date1;
    private TextView textView1;
    private Button okButton;
    private Context mContext;
    private EditText et_record;
    private ImageButton good_btn;
    private ImageButton bad_btn;
    private CustomDialogListener customDialogListener;

    public CustomDialog_record(Context context, int position, ArrayList<DrugItem> drugItemArrayList) {
        super(context);
        this.mContext = context;
        this.drugItemArrayList = drugItemArrayList;
        this.position = position;
    }
    interface CustomDialogListener{
        void onOkClicked(String text);
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
    Calendar myCalendar = Calendar.getInstance();

    class BtnOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            String GoodBad = "null";
            switch (view.getId()){
                case R.id.good_btn:
                    GoodBad = "good";
                    Log.v("기록", "좋아요 누름");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pop);
        drugName = (TextView) findViewById(R.id.drugName);
        drugName.setText(drugItemArrayList.get(position).getDrugName());

        good_btn = (ImageButton) findViewById(R.id.good_btn);
        good_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GoodBad = "good";
                Log.v("기록", "좋아요 누름");
            }
        });
        bad_btn = (ImageButton) findViewById(R.id.bad_btn);
        bad_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GoodBad = "bad";
                Log.v("기록", "싫어요 누름");
            }
        });
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        et_Date= (TextView) findViewById(R.id.to_date);
        et_Date1= (TextView) findViewById(R.id.from_date);
        et_record = (EditText) findViewById(R.id.record);
        textView1 = (TextView) findViewById(R.id.hashtag) ;
        et_record.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String result = et_record.getText().toString(); //EditText에 입력된 값 가져오기
                Log.v("키보드", result);
                switch (keyCode){
                    case KeyEvent.KEYCODE_ENTER:
                        if (event.getAction() == event.ACTION_UP) {
                            Toast.makeText(mContext.getApplicationContext(), et_record.getText(), Toast.LENGTH_LONG).show();
                            Log.v("키보드", "엔터" + result);
                            textView1.setText(result);
                            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(et_record.getWindowToken(), 0);
                        }return true;
                    case KeyEvent.KEYCODE_DEL:
                        Log.v("키보드", "백" + result);
                    default:
                        break;
                }
                return false;
            }
        });
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
                new DatePickerDialog(mContext, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
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
                new DatePickerDialog(mContext, myDatePicker1, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
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
}