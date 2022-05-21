package com.medisook.app;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

public class DruginfoActivity extends Fragment implements View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_info);
    }
    public void setContentView(int drug_info) {
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()){
            case R.id.record_pop_btn:
                CustomDialog_record dialog = new CustomDialog_record(getActivity());
                CustomDialog_record.Builder dialog_bulider = new CustomDialog_record.Builder(getActivity());
                dialog.setDialogListener(new CustomDialog_record.CustomDialogListener() {
                    @Override
                    public void onOkClicked(String text) {
                    }
                });
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                break;
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.drug_info, container, false);
        final Button record_pop_btn = (Button) rootView.findViewById(R.id.record_pop_btn);
        record_pop_btn.setOnClickListener(this);
        return rootView;
    }
}