package com.medisook.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MenuFragmentMypage extends Fragment {
    private TextView tv_hashtag;
    ArrayList<RecordItem> recordItemArrayList;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Adapter_record adapter;
    private SpannableString content;
    TextView textview;

    public MenuFragmentMypage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
    }
    public void onResume() {
        super.onResume();
    }

    private void setContentView(int mypage) {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.mypage, container, false);
        tv_hashtag = (TextView)rootView.findViewById(R.id.tv_hashtag);
        recyclerView = (RecyclerView) rootView. findViewById(R.id.recycler_view_record);

        recordItemArrayList = new ArrayList<>();

        adapter = new Adapter_record(recordItemArrayList, this);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for(int i = 0; i<100; i++){
            adapter.setArrayData(new RecordItem(i+"번째약"));
            Log.d("태그", "마이페이지 테스트");
        }
        recyclerView.setAdapter(adapter);
        return rootView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_menu_mypage, container, false);
    }
}