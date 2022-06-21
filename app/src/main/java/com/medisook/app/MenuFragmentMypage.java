package com.medisook.app;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

public class MenuFragmentMypage extends Fragment implements View.OnClickListener {
    private TextView tv_hashtag;
    ArrayList<RecordItem> recordItemArrayList;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Adapter_record adapter;
    private Button calendar;
    private Button wish;
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
        calendar = (Button)rootView.findViewById(R.id.calendar);
        calendar.setOnClickListener(this);

        wish = (Button)rootView.findViewById(R.id.wish);
        wish.setOnClickListener(this);

        recordItemArrayList = new ArrayList<>();
        adapter = new Adapter_record(recordItemArrayList, this);
        mfs = new MenuFragmentSearch();
        MenuFragmentSearch.ReadData read = mfs.new ReadData();

//
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);


        tv_hashtag = (TextView) rootView.findViewById(R.id.tv_hashtag1);


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
            }
        }, 1000);

        Log.d("테스트 : ", String.valueOf(adapter.getItemCount()));

//        for (int i = 0; i < 100; i++) {
//            adapter.setArrayData(new RecordItem(i + "번째약"));
//            Log.d("태그", "마이페이지 테스트");
//        }
//        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()) {
            case R.id.wish:
                Log.d("기록하기", "마이페이지에서 버튼");
                CustomDialog_wishlist dialog_wishlist = new CustomDialog_wishlist(getActivity());
                dialog_wishlist.setDialogListener(new CustomDialog_wishlist.CustomDialogListener() {
                    @Override
                    public void onOkClicked(ArrayList<String> text) {
                    }
                });
                dialog_wishlist.show();
                break;
            case R.id.calendar:
                Log.d("기록하기", "캘린더");
                CustomDialog_calender dialog = new CustomDialog_calender(getActivity());
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