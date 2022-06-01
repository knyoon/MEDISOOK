package com.medisook.app;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomDialog extends AlertDialog implements View.OnClickListener{
    ArrayList<FilterItem> filterItemArrayList, filtered_filterList;
    ArrayList<String> listItemArrayList, sym_list;
    LinearLayoutManager linearLayoutManager;
    EditText searchET;
    RecyclerView recyclerView, recyclerView_list;
    Adapter_filter adapter;
    Adapter_list adapter_list;
    private Button okButton;
    private Context context;
    private CustomDialogListener customDialogListener;
    int btn_pos;
    public CustomDialog(Context context, int btn_pos) {
        super(context);
        this.context = context;
        this.btn_pos = btn_pos;
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
        setContentView(R.layout.redpop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        okButton.setOnClickListener(this);
        searchET = findViewById(R.id.search_bar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_filter);
        recyclerView_list = (RecyclerView)findViewById(R.id.recycler_view_list);
        filtered_filterList = new ArrayList<>();
        filterItemArrayList = new ArrayList<>();
        listItemArrayList = new ArrayList<>();
        adapter = new Adapter_filter(filterItemArrayList, listItemArrayList, adapter_list, this);
        //adapter_list = new Adapter_list(listItemArrayList, adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        recyclerView_list.setAdapter(adapter_list);
        adapter.notifyDataSetChanged();
        //adapter_list.notifyDataSetChanged();
        //adapter_list.setArrayData("안녕");
//        Log.d("리스트", String.valueOf(listItemArrayList.size()));
//        for(int i = 0; i<listItemArrayList.size(); i++){
//
//        }
        try{
            sym_list = new ArrayList<String>();
            File file=new File("C:\\Users\\KONG\\Desktop\\medisook\\app\\src\\main\\java\\com\\medisook\\app\\sym.txt");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            FileReader fr=new FileReader(file);
            FileInputStream input=new FileInputStream("C:\\Users\\KONG\\Desktop\\medisook\\app\\src\\main\\java\\com\\medisook\\app\\sym.txt");
            Log.d("리스트", "test");
            InputStreamReader reader=new InputStreamReader(input,"UTF-8");
            BufferedReader in=new BufferedReader(reader);
            String readLine = null;
            while( ( readLine =in.readLine()) != null ){
                sym_list.add(readLine);
                Log.d("리스트", readLine);
            }
//            fr.close();
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        String[] sym_list = {"변비소염[항염]","가려움", "허리통증", "비타민 보급", "관절염", "피곤함", "벌레물린데", "습진","두드러기", "생리통"};
        String[] ingr_list = {"덱시부프로펜", "시스플라틴", "디만니톨", "클로닉신리시네이트", "L-아스파르트산-L-오르니틴","니모디핀", "돔페리돈","아시클로버","메드록시프로게스테론아세테이트","네틸마이신황산염", "베쿠로늄브롬화물"};
        String[] qesitm_list = {"임부 주의", "노인 주의", "어린이 주의", "졸음 주의", "음주 주의", "흡연 주의", "공복 주의"};
        switch (btn_pos){
            case 1:

                for(int i = 0; sym_list.length > i; i++){
                    adapter.setArrayData(new FilterItem(sym_list[i]));
                }
                break;
            case 2:
                for(int i = 0; ingr_list.length > i; i++){
                    adapter.setArrayData(new FilterItem(ingr_list[i]));
                }
                break;
            case 3:
                for(int i = 0; qesitm_list.length > i; i++){
                    adapter.setArrayData(new FilterItem(qesitm_list[i]));
                }
                break;
        }
        adapter.setOnItemClicklistener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewHolder_filter holder, View view, int position) {
            }
        });
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
        //recyclerView_list.setAdapter(adapter_list);
        searchET.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String searchText = searchET.getText().toString();
                switch (i){
                    case KeyEvent.KEYCODE_ENTER:
                        if (keyEvent.getAction() == keyEvent.ACTION_UP) {
                            Log.d("키보드", searchText);
                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(searchET.getWindowToken(), 0);
                        } return true;
                    case KeyEvent.KEYCODE_DEL:
                }
                return false;
            }
        });
    }
//    protected void onResume() {
//        onResume();
//    }
    public void searchFilter(String searchText){
        filtered_filterList.clear();
        for (int i=0; i<filterItemArrayList.size(); i++){
            if(filterItemArrayList.get(i).getFilterName().toLowerCase().contains(searchText.toLowerCase())){
                filtered_filterList.add(filterItemArrayList.get(i));
            }
        }
        adapter.filterList(filtered_filterList);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_ok_btn:
                String text = searchET.getText().toString();
                //customDialogListener.onOkClicked(text);
                ArrayList<String> data = new ArrayList<>();
                ArrayList<FilterItem> filterItemArrayList = adapter.getFilterItemArrayList();
                for (int i = 0; i<filterItemArrayList.size(); i++){
                    FilterItem filteredItem = filterItemArrayList.get(i);
                    if(filteredItem.isSelected() == true) {
                        //adapter_list.setArrayData(String.valueOf(filterItemArrayList.get(i)));
                        Log.d("필터", filteredItem.getFilterName());
                        data.add(filteredItem.getFilterName());
                    }
                    //adapter.setArrayData(new FilterItem(i+" 번째 필터"));
                }
//                Toast.makeText(this.context, "Selected filter : "+data, Toast.LENGTH_LONG).show();
                //String text = searchET.getText().toString();
                customDialogListener.onOkClicked(data);
                dismiss();
                break;
        }
    }
}
