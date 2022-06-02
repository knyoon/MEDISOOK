package com.medisook.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

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

    private static String IP_ADDRESS = "192.168.18.87:80";
    private static String TAG = "메롱";
    private EditText mEditTextName;
    private EditText mEditTextCountry;
    private TextView mTextViewResult;
    private ArrayList<DrugItem> mArrayList;
    private Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private EditText mEditTextSearchKeyword;
    private String mJsonString;
    private String searchText;
    String postParameters;
    ArrayList<DrugItem> drugItemArrayList, filtered_drugList;
    ArrayList<String> listItemArrayList, total_list;
    String[] parameter ={"efcy1", "efcy2", "efcy3","exc1","exc2","exc3","who1","who2","who3"};
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView, recyclerView_list;
    Adapter adapter;
    Adapter_list adapter_list;
    int btn_pos;
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
            String postParameters = "Efcy1=" + params[1];
            try {
                //String searchDrug="Data="+searchET.getText().toString();
                Log.d("과연", postParameters);
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(50000);
                httpURLConnection.setConnectTimeout(50000);
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
    private class ReadData extends AsyncTask<String, Void, String> {

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
            if(params[1]=="0"){
                postParameters = "Data=" + searchText;
            }
            else if (params[1]=="1"){
                parameter[0]= "Efcy1=" + total_list.get(0);
                parameter[1] = "&Efcy2=" + total_list.get(1);
                parameter[2] = "&Efcy3=" + total_list.get(2);
                parameter[3] = "&Except1=" + total_list.get(3);
                parameter[4] = "&Except2=" + total_list.get(4);
                parameter[5] = "&Except3=" + total_list.get(5);
                parameter[6] = "&Who1=" + total_list.get(6);
                parameter[7] = "&Who2=" + total_list.get(7);
                parameter[8] = "&Who3=" + total_list.get(8);
            }
            try {
                //String searchDrug="Data="+searchET.getText().toString();
//                Log.d("과연", postParameters);
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(50000);
                httpURLConnection.setConnectTimeout(50000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                if(params[1]=="0"){
                    outputStream.write(postParameters.getBytes("UTF-8"));
                }
                else if (params[1]=="1"){
                    for(int i=0;i<9;i++){
                        outputStream.write(parameter[i].getBytes("UTF-8"));
                        Log.d("과연", parameter[i]);
                    }
                }
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
        String TAG_NAME = "DRUG_NAME";
        String TAG_ENTP = "ENTP_NAME";
        String TAG_IMAGE = "IMAGE";
        String TAG_DCODE ="DRUG_CODE";
        String TAG_CLASSN ="CLASS_NAME";
        String TAG_QNT ="QNT";
        String TAG_OTC ="OTC";
        String TAG_CHART ="CHART";
        String TAG_EFCY ="EFCY";
        String TAG_USE ="USEMETHOD";
        String TAG_QESITM ="QESITM";
        String TAG_DEPOSIT ="DEPOSIT";
        String TAG_TERM ="VALID_TERM";
        String TAG_CONTENT ="TOTAL_CONTENT";
        String TAG_MAINGR ="MAIN_INGR";
        String TAG_TINGR ="INGR_NAME";

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String entp = item.getString(TAG_ENTP);
                String image = item.getString(TAG_IMAGE);
                String drugcode = item.getString(TAG_DCODE);
                String classname = item.getString(TAG_CLASSN);
                String qnt = item.getString(TAG_QNT);
                String otc = item.getString(TAG_OTC);
                String chart = item.getString(TAG_CHART);
                String efcy = item.getString(TAG_EFCY);
                String usemethod = item.getString(TAG_USE);
                String qesitm = item.getString(TAG_QESITM);
                String deposit = item.getString(TAG_DEPOSIT);
                String term = item.getString(TAG_TERM);
                String totalcontent = item.getString(TAG_CONTENT);
                String mainingr = item.getString(TAG_MAINGR);
                String ingrname = item.getString(TAG_TINGR);

                DrugItem drugData = new DrugItem();

                drugData.setDrugName(name);
                drugData.setDrugImg(image);
                drugData.setDrugentp(entp);
                drugData.setDrugcode(drugcode);
                drugData.setClassname(classname);
                drugData.setQnt(qnt);
                drugData.setOtc(otc);
                drugData.setChart(chart);
                drugData.setEfcy(efcy);
                drugData.setUsemethod(usemethod);
                drugData.setQesitm(qesitm);
                drugData.setTerm(term);
                drugData.setDeposit(deposit);
                drugData.setTotalcontent(totalcontent);
                drugData.setMainingr(mainingr);
                drugData.setIngrname(ingrname);

                adapter.setArrayData(drugData);
                Log.d(TAG, drugData.getDrugImg().toString());
            }
            adapter.notifyDataSetChanged();



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View dialoglayout = inflater.inflate(R.layout.redpop, null);
        total_list = new ArrayList<>();

        switch (v.getId()){
            case R.id.red_filter_btn:
                btn_pos = 1;
                CustomDialog dialog = new CustomDialog(getActivity(), 1);
                CustomDialog.Builder dialog_bulider = new CustomDialog.Builder(getActivity());
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> list_efcy) {
                        drugItemArrayList.clear();
                        if(list_efcy.size() == 0 || list_efcy.size() >3 ){
                            Log.d("리스트", "리스트 : " + list_efcy.size());
                            Toast.makeText(dialog.getContext(),"증상을 1개 이상 3개 이하로 선택해 주세요." ,Toast.LENGTH_LONG ).show();
                        } else {
                            CustomDialog dialog2 = new CustomDialog(getActivity(), 2);
                            CustomDialog.Builder dialog2_bulider = new CustomDialog.Builder(getActivity());
                            dialog2.setDialogListener(new CustomDialog.CustomDialogListener() {
                                @Override
                                public void onOkClicked(ArrayList<String> list_ingr) {
                                    CustomDialog dialog3 = new CustomDialog(getActivity(), 3);
                                    CustomDialog.Builder dialog3_bulider = new CustomDialog.Builder(getActivity());
                                    dialog3.setDialogListener(new CustomDialog.CustomDialogListener() {
                                        @Override
                                        public void onOkClicked(ArrayList<String> list_qesitm) {
                                            for (int i = 0; i < list_qesitm.size(); i++) {
                                                adapter_list.setArrayData(list_qesitm.get(i), 3);
                                                total_list.add(list_qesitm.get(i));
                                            }
                                            int count_list = list_qesitm.size();
                                            while(count_list<3){
                                                count_list += 1;
                                                total_list.add("슬라임");
                                            }
                                            ReadData insert = new ReadData();
                                            insert.execute("http://" + IP_ADDRESS + "/filterserch.php", "1");
                                            adapter_list.notifyDataSetChanged();
                                            Log.d("리스트", "list size : "+drugItemArrayList.size());
                                        }
                                    });
                                    dialog3.show();
                                    dialog3.getWindow().clearFlags(
                                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                                    for (int i = 0;i<list_ingr.size();i++){
                                        adapter_list.setArrayData(list_ingr.get(i), 2);
                                        total_list.add(list_ingr.get(i));
                                    }
                                    int count_list = list_ingr.size();
                                    while(count_list<3){
                                        count_list += 1;
                                        total_list.add("슬라임");
                                    }
                                    adapter_list.notifyDataSetChanged();
                                }
                            });
                            dialog2.show();
                            dialog2.getWindow().clearFlags(
                                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                            for (int i = 0;i<list_efcy.size();i++){

                                adapter_list.setArrayData(list_efcy.get(i), 1);
                                total_list.add(list_efcy.get(i));
                            }
                            int count_list = list_efcy.size();
                            while(count_list<3){
                                count_list += 1;
                                total_list.add("슬라임");
                                Log.d("리스트", "total list size : "+total_list.size());
                            }
                            adapter_list.notifyDataSetChanged();
                        }
                    }
                });
                dialog.show();
                dialog.getWindow().clearFlags(
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                break;
//            case R.id.green_filter_btn:
//                btn_pos = 2;
//                CustomDialog dialog2 = new CustomDialog(getActivity(), 2);
//                CustomDialog.Builder dialog2_bulider = new CustomDialog.Builder(getActivity());
//                dialog2.setDialogListener(new CustomDialog.CustomDialogListener() {
//                    @Override
//                    public void onOkClicked(ArrayList<String> list_ingr) {
//                        for (int i = 0;i<list_ingr.size();i++){
//                            adapter_list.setArrayData(list_ingr.get(i), 2);
//                            Log.d("리스트_2", "ingr : " + list_ingr.size());
//                        } adapter_list.notifyDataSetChanged();
//                    }
//                });
//                dialog2.show();
//                dialog2.getWindow().clearFlags(
//                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//                break;
//            case R.id.yellow_filter_btn:
//                btn_pos = 3;
//                CustomDialog dialog3 = new CustomDialog(getActivity(), 3);
//                CustomDialog.Builder dialog3_bulider = new CustomDialog.Builder(getActivity());
//                dialog3.setDialogListener(new CustomDialog.CustomDialogListener() {
//                    @Override
//                    public void onOkClicked(ArrayList<String> list_qesitm) {
//                        for (int i = 0;i<list_qesitm.size();i++){
//                            adapter_list.setArrayData(list_qesitm.get(i), 2);
//                            Log.d("리스트_2", "ingr : " + list_qesitm.size());
//                        } adapter_list.notifyDataSetChanged();
//                    }
//                });
//                dialog3.show();
//                dialog3.getWindow().clearFlags(
//                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//                break;
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
        //linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getActivity());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
        //recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView_list.setLayoutManager(layoutManager);
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
                searchText = searchET.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                       if (keyEvent.getAction() == keyEvent.ACTION_UP && searchText.isEmpty()==false) {
                           Log.d("test", "searchText : "+searchText);
                           drugItemArrayList.clear();
                           ReadData read = new ReadData();
                           read.execute("http://" + IP_ADDRESS + "/test1.php","0");
                           Log.d("리스트", "list size_2 : "+drugItemArrayList.size());
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