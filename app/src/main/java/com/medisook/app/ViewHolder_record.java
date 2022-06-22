package com.medisook.app;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder_record extends RecyclerView.ViewHolder {
    public ImageView drugImage;
    public TextView drugName;
    public TextView hashtag1;
    public TextView hashtag2;
    public TextView hashtag3;
    ConstraintLayout recordbox;
    ViewHolder_record(Context context, View itemView){
        super(itemView);
        drugName = itemView.findViewById(R.id.record_drugname);
        drugImage = itemView.findViewById(R.id.record_drugImage);
        hashtag1 = itemView.findViewById(R.id.tv_hashtag1);
        hashtag2 = itemView.findViewById(R.id.tv_hashtag2);
        hashtag3 = itemView.findViewById(R.id.tv_hashtag3);
        recordbox = itemView.findViewById(R.id.record_box);
//
//        recordbox.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Log.d("기록하기", "롱클릭");
//                AlertDialog.Builder builder = new AlertDialog.Builder();
//                builder.setMessage("이 기록을 삭제하시겠습니까?");
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//                return false;
//            }
//        });
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
