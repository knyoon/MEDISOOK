package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    ViewHolder(Context context, View itemView){
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
        imageView = itemView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strText = textView.getText().toString();
                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
