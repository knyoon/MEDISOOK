package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_list extends RecyclerView.Adapter<ViewHolder_list> {
    private ArrayList<String> listItemArrayList;
    public Adapter_list(ArrayList<String> listItemArrayList, MenuFragmentSearch activity) {
        this.listItemArrayList = listItemArrayList;
    }
    @NonNull
    public ViewHolder_list onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_list, parent, false);
        ViewHolder_list viewholder_list = new ViewHolder_list(context, view);
        return viewholder_list;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder_list holder, int position) {
        final int pos = position;
        holder.txt.setText(listItemArrayList.get(pos)); //삭제해도 되나?
        holder.txt.setTag(listItemArrayList.get(position));
        holder.txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
    }
    public int getItemCount() {
        return listItemArrayList.size();
    }
    public void setArrayData(String strData){
        listItemArrayList.add(strData);
    }
//    public void filterList(ArrayList<FilterItem> filteredList){
//        filterItemArrayList = filteredList;
//        notifyDataSetChanged();
//    }
}