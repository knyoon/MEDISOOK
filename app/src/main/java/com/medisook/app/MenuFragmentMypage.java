package com.medisook.app;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;
import static com.medisook.app.MenuFragmentSearch.username;

public class MenuFragmentMypage extends Fragment implements View.OnClickListener {
    private TextView tv_hashtag1, tv_hashtag2, tv_hashtag3;
    ArrayList<RecordItem> recordItemArrayList;
    ArrayList<String> wishlistItemArrayList;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Adapter_record adapter;
    private Button calendar;
    private Button wish;
    private TextView name;

    public MenuFragmentMypage() {
    }
    MenuFragmentSearch mfs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.mypage, container, false);
        ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.record_list, container, false);
        calendar = (Button)rootView.findViewById(R.id.calendar);
        calendar.setOnClickListener(this);

        wish = (Button)rootView.findViewById(R.id.wish);
        wish.setOnClickListener(this);

        name = (TextView)rootView.findViewById(R.id.name);
        name.setText(username);
        recordItemArrayList = new ArrayList<>();
        adapter = new Adapter_record(recordItemArrayList, this);
        mfs = new MenuFragmentSearch();
        MenuFragmentSearch.ReadData read = mfs.new ReadData();
        MenuFragmentSearch.ReadData read2 = mfs.new ReadData();

//
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);


        tv_hashtag1 = (TextView) rootView2.findViewById(R.id.tv_hashtag1);
        tv_hashtag2 = (TextView) rootView2.findViewById(R.id.tv_hashtag2);
        tv_hashtag3 = (TextView) rootView2.findViewById(R.id.tv_hashtag3);



        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();

        read.execute("http://" + IP_ADDRESS + "/readrecord.php", "2");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recordItemArrayList = read.getRecord();
                for (int i = 0; i < recordItemArrayList.size(); i++) {
                    adapter.setArrayData(recordItemArrayList.get(i));
                    Log.d("테스트", "리스트 넘어오는지 : "+(recordItemArrayList.get(i).getDrugImg()));
                }
                adapter.notifyDataSetChanged();
                Log.d("테스트", "해시태그 널인지"+tv_hashtag1.getText().toString());
                if(tv_hashtag1.getText().toString().contains("null")){
                    tv_hashtag1.setText(null);
                }
                if(tv_hashtag2.getText().toString().contains("null")){
                    tv_hashtag2.setText(null);
                }
                if(tv_hashtag3.getText().toString().contains("null")){
                    tv_hashtag3.setText(null);
                }
                wishlistItemArrayList = read.getWishlist();
            }
        }, 300);
        read2.execute("http://" + IP_ADDRESS + "/readwish2.php", "2");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wishlistItemArrayList = read2.getWishlist();
            }
        }, 1000);
        Log.d("테스트 : ", String.valueOf(adapter.getItemCount()));

        return rootView;
    }

    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()) {
            case R.id.wish:
                Log.d("기록하기", "마이페이지에서 버튼");
                CustomDialog_wishlist dialog_wishlist = new CustomDialog_wishlist(getActivity(), wishlistItemArrayList);
                dialog_wishlist.setDialogListener(new CustomDialog_wishlist.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> text) {
                    }
                });
                dialog_wishlist.show();
                break;
            case R.id.calendar:
                Log.d("기록하기", "캘린더");
                CustomDialog_calender dialog = new CustomDialog_calender(getActivity(), recordItemArrayList);
                CustomDialog_record.Builder dialog_bulider = new CustomDialog_record.Builder(getActivity());
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> text) {
                        //txt.setText(text);
                    }
                });
                dialog.show();
                break;
        }
    }
}