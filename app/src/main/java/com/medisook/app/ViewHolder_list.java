package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHolder_list extends RecyclerView.ViewHolder {
    public TextView txt;
    ViewHolder_list(Context context, View itemView){
        super(itemView);
//        itemView.setOnLongClickListener(new View.OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View view) {
//                int position = getAbsoluteAdapterPosition();
//                if(position != RecyclerView.NO_POSITION){
//
//                }
//                return false;
//            }
//        });
        txt = (TextView) itemView.findViewById(R.id.list_content);
        txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strText = txt.getText().toString();
                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
