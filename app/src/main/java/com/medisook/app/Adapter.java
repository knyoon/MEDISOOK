package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> arrayList;
    ArrayList<DrugItem> drugItemArrayList;
    Activity activity;
    private Intent intent;

    public Adapter(ArrayList<DrugItem> drugItemArrayList, Activity activity) {
        this.drugItemArrayList = drugItemArrayList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public com.medisook.app.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drug_list,parent, false);
        com.medisook.app.ViewHolder viewholder = new com.medisook.app.ViewHolder(context, view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.medisook.app.ViewHolder holder, int position) {
        //String text = arrayList.get(position);
        holder.textView.setText(drugItemArrayList.get(position).getDrugName());
        holder.textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.v("toast", "adapter에서 테스트");
                intent = new Intent(view.getContext(), DruginfoActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drugItemArrayList.size();
    }
    public void setArrayData(DrugItem strData){
        drugItemArrayList.add(strData);
    }
    public void filterList(ArrayList<DrugItem> filteredList){
            drugItemArrayList = filteredList;
            notifyDataSetChanged();
        }
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView drugName;
//        public ViewHolder(Context context, @NonNull View itemView){
//            super(itemView);
//            this.drugName = itemView.findViewById(R.id.drugName);
//        }
//    }

}

