package com.medisook.app;
// https://3001ssw.tistory.com/201

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_record extends RecyclerView.Adapter<ViewHolder_record> {
    ArrayList<RecordItem> recordItemArrayList;
    RecordItem recordItem;
    Activity activity;

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
        holder.hashtag1.setText(recordItemArrayList.get(position).getTag1());
        holder.hashtag1.setPadding(40, 0, 40, 0);
        holder.hashtag2.setText(recordItemArrayList.get(position).getTag2());
        holder.hashtag2.setPadding(40, 0, 40, 0);
        holder.hashtag3.setText(recordItemArrayList.get(position).getTag3());
        holder.hashtag3.setPadding(40, 0, 40, 0);

        if(recordItemArrayList.get(position).getGoodbad()=="bad"){
            holder.recordbox.setBackground(activity.getResources().getDrawable(R.drawable.red_box));
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

