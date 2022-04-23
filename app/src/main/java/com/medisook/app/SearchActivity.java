package com.medisook.app;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button red_filter_btn;
    private Button green_filter_btn;
    private Button yellow_filter_btn;
    private TextView txt;
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

//        Dialog red_popup = new Dialog(SearchActivity.this);
        red_filter_btn = (Button) findViewById(R.id.red_filter_btn);
        green_filter_btn = (Button)findViewById(R.id.green_filter_btn);
        yellow_filter_btn = (Button)findViewById(R.id.yellow_filter_btn);
        txt = (TextView) findViewById(R.id.txt);
        red_filter_btn.setOnClickListener(this);
        green_filter_btn.setOnClickListener(this);
        yellow_filter_btn.setOnClickListener(this);
        query2();
//        red_popup.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        red_popup.setContentView(R.layout.redpop);
//        red_filter_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                red_popup.show();
//                red_popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                Button popup_ok_btn = (Button) red_popup.findViewById(R.id.popup_ok_btn);
//                popup_ok_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        red_popup.dismiss();
//                    }
//                });
//            }
//        });
    }
    public void query2()
    {
        Log.v("Android"," MSSQL Connect Example.");
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Log.v("isConnection","MSSQL open");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;database=seyoung";
            String id="medisook";
            String password="1715231";
            conn= DriverManager.getConnection(connectionUrl,id,password);
            Statement stmt = conn.createStatement();

            conn.close();
        } catch (Exception e) {
            Log.w("Error connection","" + e.getMessage());
        }
    }
    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View dialoglayout = inflater.inflate(R.layout.redpop, null);
        switch (v.getId()){
            case R.id.red_filter_btn:
                final String[] items = new String[]{"가", "나"};
                final boolean[] checkedItems = {false, false, false, true};
                //CustomDialog.Builder dialog = new CustomDialog.Builder(SearchActivity.this);
                CustomDialog dialog = new CustomDialog(this);
                //dialog.setView(dialoglayout );
                //dialog.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        Log.d("체크박스", "1");
//                    }
//                });
//                dialog.setMultiChoiceItems(items, checkedItems, DialogInterface.OnMultiChoiceClickListener(){
//
//            });
//                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
//                    @Override
//                    public void onOkClicked(String text) {
//                        txt.setText(text);
//                    }
//                });
                dialog.show();
                break;
            case R.id.green_filter_btn:
                CustomDialog dialog2 = new CustomDialog(this);
                dialog2.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                dialog2.show();
                break;
            case R.id.yellow_filter_btn:
                CustomDialog dialog3 = new CustomDialog(this);
                dialog3.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                dialog3.show();
                break;
        }
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