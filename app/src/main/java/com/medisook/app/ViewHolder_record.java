package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_record extends RecyclerView.ViewHolder {
    public ImageView drugImage;
    public TextView drugName;
    ViewHolder_record(Context context, View itemView){
        super(itemView);
        drugName = itemView.findViewById(R.id.drugName);
//        drugImage = itemView.findViewById(R.id.drugImage);
//        drugImage.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                String strText = drugName.getText().toString();
//                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}