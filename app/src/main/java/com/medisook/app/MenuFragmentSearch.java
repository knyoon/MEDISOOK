package com.medisook.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MenuFragmentSearch extends Fragment implements View.OnClickListener {
    private Button red_filter_btn;
    private Button green_filter_btn;
    private Button yellow_filter_btn;
    private TextView txt;

    private static String IP_ADDRESS = "172.30.1.14:1719";
    private static String TAG = "메롱";
    private EditText mEditTextName;
    private EditText mEditTextCountry;
    private TextView mTextViewResult;
    private ArrayList<DrugItem> mArrayList;
    private Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;

    ArrayList<DrugItem> drugItemArrayList, filtered_drugList;
    ArrayList<String> listItemArrayList;
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView, recyclerView_list;
    Adapter adapter;
    Adapter_list adapter_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }
    public void onResume() {
        super.onResume();
    }
    private void setContentView(int search) {
    }

    private class InsertData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                Log.d("과연", result);
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {
            Log.d("과연", "test");
            String serverURL = params[0];
            String postParameters = "Data=" + params[1];


            try {

                //String searchDrug="Data="+searchET.getText().toString();
                Log.d("과연", postParameters);
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData : Error ", e);
                errorString = e.toString();

                return null;
            }
        }
    }

    private void showResult(){

        String TAG_JSON="drug";
        String TAG_ID = "DRUG_NAME";
        String TAG_NAME = "ENTP_NAME";
        String TAG_IMAGE ="IMAGE";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String id = item.getString(TAG_ID);
                String name = item.getString(TAG_NAME);
                String image = item.getString(TAG_IMAGE);

                DrugItem drugData = new DrugItem();

                drugData.setDrugName(id);
                drugData.setDrugImg(image);

                adapter.setArrayData(drugData);
                Log.d(TAG, drugData.getDrugImg().toString());
            }

            adapter.notifyDataSetChanged();



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
//    public void searchDrug(String searchText){
//        filtered_drugList.clear();
//        for (int i=0; i<drugItemArrayList.size(); i++){
//            if(drugItemArrayList.get(i).getDrugName().toLowerCase().contains(searchText.toLowerCase())){
//                filtered_drugList.add(drugItemArrayList.get(i));
//            }
//        }
//        adapter.filterList(filtered_drugList);
//    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View dialoglayout = inflater.inflate(R.layout.redpop, null);
        switch (v.getId()){
            case R.id.red_filter_btn:
                CustomDialog dialog = new CustomDialog(getActivity());
                CustomDialog.Builder dialog_bulider = new CustomDialog.Builder(getActivity());
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> list) {
                        for (int i = 0;i<list.size();i++){
                            adapter_list.setArrayData(list.get(i));
                            Log.d("리스트_2", list.get(i));
                        } adapter_list.notifyDataSetChanged();
                    }
                });
                dialog.show();
                Log.d("테스트", "버튼 눌리는지");
                dialog.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                break;
            case R.id.green_filter_btn:
                CustomDialog dialog2 = new CustomDialog(getActivity());
                CustomDialog.Builder dialog2_bulider = new CustomDialog.Builder(getActivity());
                dialog2.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> text) {
//                        txt.setText(text);
                    }
                });
                dialog2.show();

                break;
            case R.id.yellow_filter_btn:
                final String[] items = new String[]{"가", "나"};
                final boolean[] checkedItems = {false, false, false, true};
                CustomDialog dialog3 = new CustomDialog(getActivity());
                CustomDialog.Builder dialog3_bulider = new CustomDialog.Builder(getActivity());
                dialog3.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> text) {
                        //txt.setText(text);
                    }
                });
                dialog3.show();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.search, container, false);
        EditText searchET = (EditText) rootView.findViewById(R.id.search_bar);
        recyclerView = (RecyclerView) rootView. findViewById(R.id.recycler_view);
        recyclerView_list = (RecyclerView) rootView. findViewById(R.id.recycler_view_list);

        filtered_drugList = new ArrayList<>();
        drugItemArrayList = new ArrayList<>();
        //recyclerview 선언부
        listItemArrayList = new ArrayList<>();

        adapter = new Adapter(drugItemArrayList, this);
        adapter_list = new Adapter_list(listItemArrayList, this);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView_list.setAdapter(adapter_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        drugItemArrayList.clear();
        adapter.notifyDataSetChanged();
        adapter_list.notifyDataSetChanged();
//        for(int i = 0; i<100; i++){
//            adapter.setArrayData(new DrugItem(i+"번째 약"));
//        }
//        searchET.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });
        recyclerView.setAdapter(adapter);
        red_filter_btn = (Button) rootView. findViewById(R.id.red_filter_btn);
        green_filter_btn = (Button)rootView. findViewById(R.id.green_filter_btn);
        yellow_filter_btn = (Button)rootView. findViewById(R.id.yellow_filter_btn);
        txt = (TextView)rootView. findViewById(R.id.txt);
        red_filter_btn.setOnClickListener(this);
        green_filter_btn.setOnClickListener(this);
        yellow_filter_btn.setOnClickListener(this);
        searchET.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String searchText = searchET.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                       if (keyEvent.getAction() == keyEvent.ACTION_UP) {
                           drugItemArrayList.clear();
                           InsertData insert = new InsertData();
                           insert.execute("http://" + IP_ADDRESS + "/test1.php", searchText);
                           InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                           imm.hideSoftInputFromWindow(searchET.getWindowToken(), 0);
                       } return true;
                    case KeyEvent.KEYCODE_DEL:
                }
                return false;
            }
        });
        return rootView;
    }
}