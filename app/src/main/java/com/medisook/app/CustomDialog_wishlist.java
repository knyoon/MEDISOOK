package com.medisook.app;

import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CustomDialog_wishlist extends AlertDialog implements View.OnClickListener{
    ArrayList<String> wishlistItemArrayList;
    RecyclerView recyclerView_wishlist;
    Adapter_wishlist adapter_wishlist;
    private Button okButton;
    private Context context;
    private CustomDialogListener customDialogListener;
    public CustomDialog_wishlist(Context context, ArrayList<String> wishlistItemArrayList) {
        super(context);
        this.context = context;
        this.wishlistItemArrayList = wishlistItemArrayList;
    }
    interface CustomDialogListener{
        void onOkClicked(ArrayList<String> text);
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist_pop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        okButton.setOnClickListener(this);
        recyclerView_wishlist = (RecyclerView)findViewById(R.id.recycler_view_wishlist);
        wishlistItemArrayList = new ArrayList<>();
        adapter_wishlist = new Adapter_wishlist(wishlistItemArrayList, this);
        recyclerView_wishlist.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView_wishlist.setAdapter(adapter_wishlist);


        MenuFragmentSearch mfs = new MenuFragmentSearch();
        MenuFragmentSearch.ReadData read = mfs.new ReadData();
        read.execute("http://" + IP_ADDRESS + "/readwish2.php", "2");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wishlistItemArrayList = read.getWishlist();
                for (int i = 0; i < wishlistItemArrayList.size(); i++) {
                    adapter_wishlist.setArrayData(wishlistItemArrayList.get(i));
                    Log.d("테스트", "리스트 넘어오는지 : "+(wishlistItemArrayList.get(i)));
                }
                adapter_wishlist.notifyDataSetChanged();
            }
        }, 1000);
//        adapter_wishlist.setArrayData("안녕");
//        adapter_wishlist.setArrayData("안녕");
//        adapter_wishlist.setArrayData("안녕");
//        adapter_wishlist.setArrayData("안녕");

        adapter_wishlist.notifyDataSetChanged();


//        Log.d("리스트", String.valueOf(listItemArrayList.size()));
//        for(int i = 0; i<listItemArrayList.size(); i++){
//        }
//        searchET.setOnKeyListener(new View.OnKeyListener(){
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                String searchText = searchET.getText().toString();
//                switch (i){
//                    case KeyEvent.KEYCODE_ENTER:
//                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
//                            Log.d("키보드", searchText);
//                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(searchET.getWindowToken(), 0);
//                        } return true;
//                    case KeyEvent.KEYCODE_DEL:
//                }
//                return false;
//            }
//        });
    }
//    protected void onResume() {
//        onResume();
//    }
//    public void searchFilter(String searchText){
//        filtered_filterList.clear();
//        for (int i=0; i<filterItemArrayList.size(); i++){
//            if(filterItemArrayList.get(i).getFilterName().toLowerCase().contains(searchText.toLowerCase())){
//                filtered_filterList.add(filterItemArrayList.get(i));
//            }
//        }
//        adapter.filterList(filtered_filterList);
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_ok_btn:
//                String text = searchET.getText().toString();
//                //customDialogListener.onOkClicked(text);
//                ArrayList<String> data = new ArrayList<>();
//                ArrayList<FilterItem> filterItemArrayList = adapter_wishlist.getFilterItemArrayList();
//                for (int i = 0; i<filterItemArrayList.size(); i++){
//                    FilterItem filteredItem = filterItemArrayList.get(i);
//                    if(filteredItem.isSelected() == true) {
//                        //adapter_list.setArrayData(String.valueOf(filterItemArrayList.get(i)));
//                        Log.d("필터", filteredItem.getFilterName());
//                        data.add(filteredItem.getFilterName());
//                    }
                    //adapter.setArrayData(new FilterItem(i+" 번째 필터"));
                }
//                Toast.makeText(this.context, "Selected filter : "+data, Toast.LENGTH_LONG).show();
                //String text = searchET.getText().toString();
//                customDialogListener.onOkClicked(data);
                dismiss();
//                break;
        }
    }

