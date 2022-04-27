package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_filter extends RecyclerView.ViewHolder {
    public CheckBox checkBox;

    ViewHolder_filter(Context context, View itemView){
        super(itemView);

        checkBox = itemView.findViewById(R.id.filter_content);
//        imageView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String strText = checkBox.getText().toString();
//                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
