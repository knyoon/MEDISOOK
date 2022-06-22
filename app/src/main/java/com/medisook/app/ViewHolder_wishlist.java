package com.medisook.app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_wishlist extends RecyclerView.ViewHolder {
    public TextView txt;
    ViewHolder_wishlist(View itemView, final OnItemClickListener listener){
        super(itemView);
        txt = (TextView) itemView.findViewById(R.id.wishlist_content);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
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

//        txt.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
////                String strText = txt.getText().toString();
////                Toast.makeText(context, strText, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
