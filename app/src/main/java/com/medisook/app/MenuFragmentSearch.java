package com.medisook.app;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


public class MenuFragmentSearch extends Fragment implements View.OnClickListener {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);


    }

    private void setContentView(int search) {
    }

    public void searchFilter(String searchText){
        filteredList.clear();;
        for (int i=0; i<drugItemArrayList.size(); i++){
            if(drugItemArrayList.get(i).getDrugName().toLowerCase().contains(searchText.toLowerCase())){
                filteredList.add(drugItemArrayList.get(i));
            }
        }
        adapter.filterList(filteredList);
    }
    public void query2()
    {
        Log.v("tag"," MSSQL Connect Example.");
        Connection conn = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Log.v("tag","MSSQL open");
            String connectionUrl = "jdbc:sqlserver://localhost:1433/database=seyoung";
            String id="medisook";
            String password="1715231";
            conn= DriverManager.getConnection(connectionUrl,id,password);
            Statement stmt = conn.createStatement();
            conn.close();
        } catch (Exception e) {
            Log.w("tag","" + e.getMessage());
        }
    }
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
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                dialog.show();
                break;
            case R.id.green_filter_btn:
                CustomDialog dialog2 = new CustomDialog(getActivity());
                CustomDialog.Builder dialog2_bulider = new CustomDialog.Builder(getActivity());
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
                CustomDialog dialog3 = new CustomDialog(getActivity());
                CustomDialog.Builder dialog3_bulider = new CustomDialog.Builder(getActivity());
                dialog3.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
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

        filteredList = new ArrayList<>();
        drugItemArrayList = new ArrayList<>();


        adapter = new Adapter(drugItemArrayList, this);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        for(int i = 0; i<100; i++){
            adapter.setArrayData(new DrugItem(i+"번째 약"));
        }
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = searchET.getText().toString();
                searchFilter(searchText);
            }
        });
        recyclerView.setAdapter(adapter);
        red_filter_btn = (Button) rootView. findViewById(R.id.red_filter_btn);
        green_filter_btn = (Button)rootView. findViewById(R.id.green_filter_btn);
        yellow_filter_btn = (Button)rootView. findViewById(R.id.yellow_filter_btn);
        txt = (TextView)rootView. findViewById(R.id.txt);
        red_filter_btn.setOnClickListener(this);
        green_filter_btn.setOnClickListener(this);
        yellow_filter_btn.setOnClickListener(this);
        query2();
        return rootView;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_menu_main, container, false);
    }
}