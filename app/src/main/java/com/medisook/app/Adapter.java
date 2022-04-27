package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    //private ArrayList<String> arrayList;
    ArrayList<DrugItem> drugItemArrayList;
    Activity activity;

    public Adapter(ArrayList<DrugItem> drugItemArrayList, Activity activity) {
        this.drugItemArrayList = drugItemArrayList;
        this.activity = activity;
       //arrayList = new ArrayList<>();
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
        //String text = arrayList.get(position);
        holder.drugName.setText(drugItemArrayList.get(position).getDrugName());
    }
    public int getItemCount() {
        return drugItemArrayList.size();
    }
    public void filterList(ArrayList<DrugItem> filteredList){
            drugItemArrayList = filteredList;
            notifyDataSetChanged();
        }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView drugName;
        public ViewHolder(Context context, @NonNull View itemView){
            super(itemView);
            this.drugName = itemView.findViewById(R.id.drugName);
        }
    }
    public void setArrayData(DrugItem strData){
        drugItemArrayList.add(strData);
    }
}

