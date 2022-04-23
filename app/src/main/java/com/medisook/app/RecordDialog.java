package com.medisook.app;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecordDialog extends AlertDialog implements View.OnClickListener{
    private Button okButton;
    //private EditText editText;
    private Context context;
    private RecordDialogListener recordDialogListener;

    public RecordDialog(Context context) {
        super(context);
        this.context = context;
    }
    interface RecordDialogListener{
        void onOkClicked(String text);
    }
    public void setDialogListener(RecordDialogListener recordDialogListener){
        this.recordDialogListener = recordDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_pop);
        okButton = (Button) findViewById(R.id.close_btn);
        //editText = (EditText) findViewById(R.id.editText);
        okButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close_btn:
                //Log.v("click", "test");
                //String text = editText.getText().toString();
                //customDialogListener.onOkClicked(text);
                dismiss();
                break;
        }
    }
}
