package com.medisook.app;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHolder_filter extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    ViewHolder_filter(View itemView, final OnItemClickListener listener){
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.filter_content);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String strText = checkBox.getText().toString();
                Toast.makeText(checkBox.getContext(), strText, Toast.LENGTH_SHORT).show();
                Log.d("checkbox", "test");
//                int position = getAbsoluteAdapterPosition();
//                if(listener != null){
//                    listener.onItemClick(ViewHolder_filter.this, compoundButton, position);
//                }
            }
        });
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String strText = checkBox.getText().toString();
//                int position = getAbsoluteAdapterPosition();
//                if(listener != null){
//                    listener.onItemClick(ViewHolder_filter.this, view, position);
//                }
                Toast.makeText(view.getContext(), strText, Toast.LENGTH_SHORT).show();
            }
        });
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                int position = getAbsoluteAdapterPosition();
//                if(listener != null){
//                    listener.onItemClick(ViewHolder_filter.this, view, position);
//                }
            }
        });
    }
}
