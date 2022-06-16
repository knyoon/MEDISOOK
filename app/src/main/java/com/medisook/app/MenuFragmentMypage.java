package com.medisook.app;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuFragmentMypage extends Fragment implements View.OnClickListener {
    private static final String IP_ADDRESS =  "192.168.18.51:80";
    private TextView tv_hashtag;
    ArrayList<RecordItem> recordItemArrayList;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Adapter_record adapter;
    private SpannableString content;
    private Button calendar;
    TextView textview;
    Context context;
    RecordItem recordItem;
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
        tv_hashtag = (TextView) rootView.findViewById(R.id.tv_hashtag);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_record);
        recordItemArrayList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();
        mfs = new MenuFragmentSearch();
        MenuFragmentSearch.ReadData read = mfs.new ReadData();
        read.execute("http://" + IP_ADDRESS + "/readrecord.php", "2");

  //      Bundle bundle = getArguments();
//        recordItem = new RecordItem();
  //      Log.d("테스트", "어디까지 오는지");
  //      recordItem = (RecordItem) bundle.getSerializable(("RecordItem"));
  //      adapter = new Adapter_record(recordItem, this);
   //     adapter.setArraydata(recordItem);
   //     adapter.notifyDataSetChanged();
//        for (int i = 0; i < 100; i++) {
//            adapter.setArrayData(new RecordItem(i + "번째약"));
//            Log.d("태그", "마이페이지 테스트");
//        }
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()) {
            case R.id.calendar:
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