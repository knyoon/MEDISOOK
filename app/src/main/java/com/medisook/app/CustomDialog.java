package com.medisook.app;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialog extends AlertDialog implements View.OnClickListener{
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
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redpop);
        okButton = (Button) findViewById(R.id.popup_ok_btn);
        editText = (EditText) findViewById(R.id.editText);
        okButton.setOnClickListener(this);
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
