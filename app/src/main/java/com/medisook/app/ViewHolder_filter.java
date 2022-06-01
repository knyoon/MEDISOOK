package com.medisook.app;

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
                int position = getAbsoluteAdapterPosition();
                if(listener != null){
                    listener.onItemClick(ViewHolder_filter.this, compoundButton, position);
                }
            }

            private int getAbsoluteAdapterPosition() {
                return 0;
            }

        });
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strText = checkBox.getText().toString();
                int position = getAbsoluteAdapterPosition();
                if(listener != null){
                    listener.onItemClick(ViewHolder_filter.this, view, position);
                }
                //Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
            }

            private int getAbsoluteAdapterPosition() {
                return 0;
            }
        });
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = getAbsoluteAdapterPosition();
                if(listener != null){
                    listener.onItemClick(ViewHolder_filter.this, view, position);
                }
            }

            private int getAbsoluteAdapterPosition() {
                return 0;
            }
        });
    }

}
