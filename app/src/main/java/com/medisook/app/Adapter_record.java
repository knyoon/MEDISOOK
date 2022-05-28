package com.medisook.app;
// https://3001ssw.tistory.com/201

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_record extends RecyclerView.Adapter<ViewHolder_record> {
    ArrayList<RecordItem> recordItemArrayList;
    Activity activity;

    public Adapter_record(ArrayList<RecordItem> recordItemArrayList, MenuFragmentMypage activity) {
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
        holder.drugName.setText(recordItemArrayList.get(position).getDrugName());
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
}

