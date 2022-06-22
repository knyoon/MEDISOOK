package com.medisook.app;
// https://3001ssw.tistory.com/201

import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_record extends RecyclerView.Adapter<ViewHolder_record> {
    ArrayList<RecordItem> recordItemArrayList;
    RecordItem recordItem;
    Activity activity;
    MenuFragmentSearch mf = new MenuFragmentSearch();

    public Adapter_record(ArrayList<RecordItem> recordItemArrayList, MenuFragmentMypage activity) {
//        this.recordItemArrayList = recordItemArrayList;
        this.recordItemArrayList = recordItemArrayList;
        this.activity = activity.getActivity();
    }

    @NonNull
    public com.medisook.app.ViewHolder_record onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.record_list,parent, false);
        com.medisook.app.ViewHolder_record viewholder_record = new com.medisook.app.ViewHolder_record(context, view);
        return viewholder_record;
    }

    @Override
    public void onBindViewHolder(@NonNull com.medisook.app.ViewHolder_record holder, int position) {
        String imageurl=recordItemArrayList.get(position).getDrugImg();
        if(imageurl.isEmpty()==false){
            Log.d("기록하기",recordItemArrayList.get(position).getDrugImg());
            Picasso.get()
                    .load(imageurl)
                    .error(R.drawable.drung_sampleimage)
                    .placeholder(R.drawable.drung_sampleimage)
                    .into(holder.drugImage);
        }
        else{
            Drawable drawable= activity.getResources().getDrawable(R.drawable.drung_sampleimage);
            holder.drugImage.setImageDrawable(drawable);
        }
        holder.drugName.setText(recordItemArrayList.get(position).getDrugName());
        if(recordItemArrayList.get(position).getTag1().toString().contains("null")==false){
            holder.hashtag1.setText(recordItemArrayList.get(position).getTag1());
            holder.hashtag1.setPadding(40, 0, 40, 0);
        }
        if(recordItemArrayList.get(position).getTag2().toString().contains("null")==false){
            holder.hashtag2.setText(recordItemArrayList.get(position).getTag2());
            holder.hashtag2.setPadding(40, 0, 40, 0);
        }
        if(recordItemArrayList.get(position).getTag3().toString().contains("null")==false){
            holder.hashtag3.setText(recordItemArrayList.get(position).getTag3());
            holder.hashtag3.setPadding(40, 0, 40, 0);
        }
        holder.recordbox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("이 기록을 삭제하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MenuFragmentSearch.InsertData insert = mf.new InsertData();
                        Log.d("기록 삭제", holder.drugName.toString());
                        insert.execute("http://" + IP_ADDRESS + "/edit_record.php", "2", holder.drugName.getText().toString());

                        FragmentManager fm = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        MenuFragmentMypage fragmentMypage = new MenuFragmentMypage();
                        transaction.replace(R.id.menu_frame_layout, fragmentMypage).commitAllowingStateLoss();
                    }
                });
                builder.setNegativeButton("아니오", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
        String goodbad = recordItemArrayList.get(position).getGoodbad();
        Log.d("기록하기",goodbad);
        if(goodbad.contains("bad")){
            holder.recordbox.setBackground(activity.getResources().getDrawable(R.drawable.resize_redbox));
        }

        holder.drugName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
    }
    public int getItemCount() {
        return recordItemArrayList.size();
    }
    public void setArrayData(RecordItem strData){recordItemArrayList.add(strData);
    }

//    public void setArraydata(RecordItem record) {
//        recordItemArrayList.add(record);
//    }
}

