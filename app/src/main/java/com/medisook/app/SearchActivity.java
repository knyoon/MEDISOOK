package com.medisook.app;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button red_filter_btn;
    private Button green_filter_btn;
    private Button yellow_filter_btn;
    private TextView txt;
    ArrayList<DrugItem> drugItemArrayList, filteredList;
    LinearLayoutManager linearLayoutManager;
    EditText searchET;
    RecyclerView recyclerView;
    Adapter adapter;
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
        searchET = findViewById(R.id.search_bar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        filteredList = new ArrayList<>();
        drugItemArrayList = new ArrayList<>();

//        Dialog red_popup = new Dialog(SearchActivity.this);
        red_filter_btn = (Button) findViewById(R.id.red_filter_btn);
        green_filter_btn = (Button)findViewById(R.id.green_filter_btn);
        yellow_filter_btn = (Button)findViewById(R.id.yellow_filter_btn);
        txt = (TextView) findViewById(R.id.txt);
        red_filter_btn.setOnClickListener(this);
        green_filter_btn.setOnClickListener(this);
        yellow_filter_btn.setOnClickListener(this);
        query2();
    }
    public void searchFilter(String searchText){
        filteredList.clear();;
        for (int i=0; i<drugItemArrayList.size(); i++){
            if(drugItemArrayList.get(i).getDrugName().toLowerCase().contains(searchText.toLowerCase())){
                filteredList.add(drugItemArrayList.get(i));
            }
        }
        adapter.filterList(filteredList);
    }//fd
    public void query2()
    {
        Log.v("Android"," MSSQL Connect Example.");
        Connection conn = null;
        try {
            URL url = new URL("http://192.168.18.130/test.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            Log.i("log_tag","connect!");
            Log.i("log_tag", String.valueOf(conn.getResponseCode()));

            BufferedReader rd;
            Log.i("log_tag","connect!");
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            Log.i("log_tag","connect!");
            String line;
            String result = "";
            while ((line = rd.readLine()) != null) {
                result = result.concat(line);
            }
            Log.i("log_tag",result);

            JSONArray jsonarray = new JSONArray(result);
            for (int i=0;i<jsonarray.length();i++){
                JSONObject j_data=jsonarray.getJSONObject(i);
                Log.i("log_tag","name: "+j_data.getString("DRUG_NAME"));
            }

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
                CustomDialog.Builder dialog_bulider = new CustomDialog.Builder(this);
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                dialog.show();
                break;
            case R.id.green_filter_btn:
                CustomDialog dialog2 = new CustomDialog(this);
                CustomDialog.Builder dialog2_bulider = new CustomDialog.Builder(this);
                dialog2.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                dialog2.show();
                break;
            case R.id.yellow_filter_btn:
                final String[] items = new String[]{"가", "나"};
                final boolean[] checkedItems = {false, false, false, true};
                CustomDialog dialog3 = new CustomDialog(this);
                CustomDialog.Builder dialog3_bulider = new CustomDialog.Builder(this);
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
}