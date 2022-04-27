package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_filter extends RecyclerView.Adapter<ViewHolder_filter> {
    private ArrayList<String> arrayList;
    public Adapter_filter() {
        arrayList = new ArrayList<>();
    }
    @NonNull
    public ViewHolder_filter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_list,parent, false);
        ViewHolder_filter viewholder_filter = new ViewHolder_filter(context, view);
        return viewholder_filter;
    }

    public void onBindViewHolder(@NonNull ViewHolder_filter holder, int position){
        String text = arrayList.get(position);
        holder.checkBox.setText(text);
    }
    public int getItemCount() {
        return arrayList.size();
    }
    public void setArrayData(String strData){
        arrayList.add(strData);
    }
}