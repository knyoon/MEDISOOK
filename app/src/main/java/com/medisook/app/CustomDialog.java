package com.medisook.app;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDialog extends AlertDialog implements View.OnClickListener{
    RecyclerView recyclerView;
    Adapter_filter adapter;
    private Button okButton;
    private EditText editText;
    private Context context;
    private CustomDialogListener customDialogListener;
    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }
    interface CustomDialogListener{
        void onOkClicked(String text);
    }
    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redpop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        editText = (EditText) findViewById(R.id.search_bar);
        okButton.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_filter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, RecyclerView.VERTICAL, false));
        adapter = new Adapter_filter();
        for(int i = 0; i<100; i++){
            String str = i+"번째 필터 컨텐츠";
            adapter.setArrayData(str);
        }
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_ok_btn:
                Log.v("click", "test");
                String text = editText.getText().toString();
                customDialogListener.onOkClicked(text);
                dismiss();
                break;
        }
    }
}
