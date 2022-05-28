package com.medisook.app;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DruginfoActivity extends Fragment implements View.OnClickListener{
    TextView drugName_view;
    ImageView drugImg_view;
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
        drugName_view = (TextView) rootView.findViewById(R.id.drugName);
        drugImg_view = (ImageView) rootView.findViewById(R.id.drugImage);
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        ArrayList<DrugItem> drugItem = (ArrayList<DrugItem>) bundle.getSerializable(("DrugItem"));
        String drugName = drugItem.get(position).getDrugName();
        String drugImage  = drugItem.get(position).getDrugImg();
        if(drugImage.isEmpty()==false){
            Picasso.get()
                    .load(drugImage)
                    .error(R.drawable.drung_sampleimage)
                    .placeholder(R.drawable.drung_sampleimage)
                    .into(drugImg_view);
        }
        else{
            Drawable drawable= ContextCompat.getDrawable(getContext(), R.drawable.drung_sampleimage);
            drugImg_view.setImageDrawable(drawable);
        }
        Log.d("test", "position : " + position);
        Log.d("test", "drugitem : " + drugItem.size());
        Log.d("test", "drugitem : " + drugItem.get(0));
        drugName_view.setText(drugName);

        final Button record_pop_btn = (Button) rootView.findViewById(R.id.record_pop_btn);
        record_pop_btn.setOnClickListener(this);
        return rootView;
    }
}