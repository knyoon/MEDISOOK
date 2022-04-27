package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder>   {
    private ArrayList<String> arrayList;
    public Adapter() {
        arrayList = new ArrayList<>();
    }
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drug_list,parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);
        return viewholder;
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String text = arrayList.get(position);
        holder.textView.setText(text);
    }
    public int getItemCount() {
        return arrayList.size();
    }
    public void setArrayData(String strData){
        arrayList.add(strData);
    }
}