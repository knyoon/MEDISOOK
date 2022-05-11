package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_filter extends RecyclerView.Adapter<ViewHolder_filter> {
    private ArrayList<FilterItem> filterItemArrayList;
    public Adapter_filter(ArrayList<FilterItem> filterItemArrayList, CustomDialog customDialog) {
        this.filterItemArrayList = filterItemArrayList;
    }
    @NonNull
    public com.medisook.app.ViewHolder_filter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_list, parent, false);
        com.medisook.app.ViewHolder_filter viewholder_filter = new com.medisook.app.ViewHolder_filter(context, view);
        return viewholder_filter;
    }
    @Override
    public void onBindViewHolder(@NonNull com.medisook.app.ViewHolder_filter holder, int position) {
        //final int pos = position;
        //FilterItem filterName = filterItemArrayList.get(position);
        holder.checkBox.setText(filterItemArrayList.get(position).getFilterName()); //삭제해도 되나?
        //holder.checkBox.setTag(filterItemArrayList.get(position));
        // holder.checkBox.setChecked(filterItemArrayList.get(position).isSelected());
        holder.checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                FilterItem filter = (FilterItem) cb.getTag();
                Toast.makeText(cb.getContext(), cb.getText(), Toast.LENGTH_SHORT).show();
                //filter.setSelected(cb.isChecked());
                //filterItemArrayList.get(pos).setSelected(cb.isChecked());
                //holder.filter_selected_1.setText(filterItemArrayList.get(position).getfilterName());
            }
        });
    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder_filter holder, @SuppressLint("RecyclerView") int position) {
//        final int pos = position;
//        FilterItem filterName = filterItemArrayList.get(position);
//        holder.checkBox.setText(filterName.getfilterName()); //삭제해도 되나?
//        holder.checkBox.setTag(filterItemArrayList.get(position));
//       // holder.checkBox.setChecked(filterItemArrayList.get(position).isSelected());
//        holder.checkBox.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                CheckBox cb = (CheckBox) v;
//                FilterItem filter = (FilterItem) cb.getTag();
//                Toast.makeText(cb.getContext(), cb.getText(), Toast.LENGTH_SHORT).show();
//                //filter.setSelected(cb.isChecked());
//                //filterItemArrayList.get(pos).setSelected(cb.isChecked());
//                //holder.filter_selected_1.setText(filterItemArrayList.get(position).getfilterName());
//            }
//        });
//    }
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
//    public class ViewHolder_filter extends RecyclerView.ViewHolder{
//        TextView filterName;
//        CheckBox checkBox;
//        TextView filter_selected_1;
//        TextView filter_selected_2;
//        TextView filter_selected_3;
//        public ViewHolder_filter(Context context, @NonNull View itemView){
//            super(itemView);
//            this.filterName = itemView.findViewById(R.id.filter_content);
//            this.checkBox = (CheckBox) itemView.findViewById(R.id.filter_content);
//            this.filter_selected_1 = (TextView) itemView.findViewById(R.id.filter_selected_1);
////            this.filter_selected_2 = (TextView) itemView.findViewById(R.id.filter_selected_2);
////            this.filter_selected_3 = (TextView) itemView.findViewById(R.id.filter_selected_3);
//        }
//    }
}