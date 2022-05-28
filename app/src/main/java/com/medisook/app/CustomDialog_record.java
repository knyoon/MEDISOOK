package com.medisook.app;
import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomDialog_record extends AlertDialog implements View.OnClickListener{
    TextView TextView_get, textValue;
    private EditText to_date;
    private EditText from_date;
    private TextView et_Date;
    private TextView et_Date1;
    private Button okButton;
    private Context mContext;
    private EditText et_record;
    private ImageButton good_btn;
    private ImageButton bad_btn;
    private CustomDialogListener customDialogListener;

    public CustomDialog_record(Context context) {
        super(context);
        mContext = context;
        //this.context = context;
    }
    interface CustomDialogListener{
        void onOkClicked(String text);
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    Calendar myCalendar = Calendar.getInstance();

//    class BtnOnClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view){
//            String GoodBad = "null";
//            switch (view.getId()){
//                case R.id.good_btn:
//                    GoodBad = "good";
//                    Log.v("기록", "좋아요 누름");
//                    break;
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pop);

        good_btn = (ImageButton) findViewById(R.id.good_btn);
        bad_btn = (ImageButton) findViewById(R.id.bad_btn);
        okButton = (Button) findViewById(R.id.popup_ok_btn);

        Button.OnClickListener btnObject = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GoodBad = "입력되지않음";
                switch (view.getId()){
                    case R.id.good_btn:
                        GoodBad = "good";
                        break;
                    case R.id.bad_btn:
                        GoodBad = "bad";
                        break;
                    case R.id.popup_ok_btn:
                        Log.v("기록", "오케이버튼 누름");
                        Log.v("기록",GoodBad + "최종 넘기기");
                        break;

                }
                Log.v("기록", GoodBad); //세영언니한테 넘기기
//                if(view.getId() == R.id.popup_ok_btn) {
//                    Log.v("기록",GoodBad);
//                }
//                Log.v("기록", GoodBad);
            }
        };

        findViewById(R.id.good_btn).setOnClickListener(btnObject);
        findViewById(R.id.bad_btn).setOnClickListener(btnObject);
        findViewById(R.id.popup_ok_btn).setOnClickListener(btnObject);

        et_Date= (TextView) findViewById(R.id.to_date);
        et_Date1= (TextView) findViewById(R.id.from_date);
        et_record = (EditText) findViewById(R.id.record);

        et_record.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.v("키보드", "이벤트" + et_record.getText());
                if (event.getAction() != KeyEvent.ACTION_DOWN) {
                    //et_record.setText(et_record.getText());
                    String result = et_record.getText().toString(); //EditText에 입력된 값 가져오기
                    TextView textView1 = (TextView) findViewById(R.id.hashtag) ;
                    textView1.setText(et_record.getText()) ;
                    et_record.getText().clear();
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_record.getWindowToken(), 0);
                    //Toast.makeText(mContext.getApplicationContext(), et_record.getText(), Toast.LENGTH_LONG).show();
                    Log.v("키보드", "엔터" + et_record.getText());
                    return true;
                }
                switch (keyCode) {
                    case KeyEvent.KEYCODE_1 :
                        break;
                    case KeyEvent.KEYCODE_2 :
                        break;
                    case KeyEvent.KEYCODE_3 :
                        break;
                }
                return false;
            }
        });
        okButton = (Button) findViewById(R.id.popup_ok_btn);
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
                        Log.v("태그", String.valueOf(year) + String.valueOf(month) + String.valueOf(dayOfMonth));
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
