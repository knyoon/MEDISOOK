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

public class Adapter_filter extends RecyclerView.Adapter<ViewHolder_filter> implements  OnItemClickListener{
    private ArrayList<FilterItem> filterItemArrayList;
    private ArrayList<String> listItemArrayList;
    private Adapter_list adapter_list;
    OnItemClickListener listener;
    public Adapter_filter(ArrayList<FilterItem> filterItemArrayList, ArrayList<String> listItemArrayList, Adapter_list adapter_list, CustomDialog customDialog) {
        this.filterItemArrayList = filterItemArrayList;
        this.listItemArrayList = listItemArrayList;
        this.adapter_list = adapter_list;
    }
    @NonNull
    public com.medisook.app.ViewHolder_filter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_list, parent, false);
        com.medisook.app.ViewHolder_filter viewholder_filter = new com.medisook.app.ViewHolder_filter(view, this);
        return viewholder_filter;
    }
    @Override
    public void onBindViewHolder(@NonNull com.medisook.app.ViewHolder_filter holder, int position) {
        final int pos = position;
        listItemArrayList = new ArrayList<>();
        //adapter_list = new Adapter_list(listItemArrayList, this);
        holder.checkBox.setText(filterItemArrayList.get(position).getFilterName()); //삭제해도 되나?
        holder.checkBox.setTag(filterItemArrayList.get(position));
        holder.checkBox.setChecked(filterItemArrayList.get(position).isSelected());
        holder.checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                FilterItem filter = (FilterItem) cb.getTag();
                filter.setSelected(cb.isChecked());
                filterItemArrayList.get(pos).setSelected(cb.isChecked());
                listItemArrayList.add(String.valueOf(cb.getText()));
//                Log.d("리스트", String.valueOf(listItemArrayList.size()));
            }
        });
    }
    public ArrayList<FilterItem> getFilterItemArrayList() {
        return filterItemArrayList;
    }
    public int getItemCount() {
        return filterItemArrayList.size();
    }
    public void setArrayData(FilterItem strData){
        filterItemArrayList.add(strData);
    }
    public void filterList(ArrayList<FilterItem> filteredList){
        filterItemArrayList = filteredList;
        notifyDataSetChanged();
    }
    public void setOnItemClicklistener(OnItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onItemClick(ViewHolder_filter holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
        //adapter_list.setArrayData(listItemArrayList);

    }
}