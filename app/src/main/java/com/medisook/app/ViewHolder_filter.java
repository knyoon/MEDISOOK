package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHolder_filter extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    //TextView filter_selected_1;
//    TextView filter_selected_2;
//    TextView filter_selected_3;
    ViewHolder_filter(Context context, View itemView){
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.filter_content);
        //filter_selected_1 = (TextView) itemView.findViewById(R.id.filter_selected_1);
//        filter_selected_2 = (TextView) itemView.findViewById(R.id.filter_selected_2);
//        filter_selected_3 = (TextView) itemView.findViewById(R.id.filter_selected_3);
//        checkBox = itemView.findViewById(R.id.filter_content);
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strText = checkBox.getText().toString();
                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
