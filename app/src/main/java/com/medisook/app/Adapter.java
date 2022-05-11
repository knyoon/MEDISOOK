package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> arrayList;
    ArrayList<DrugItem> drugItemArrayList;
    Activity activity;
    private Intent intent;

    public Adapter(ArrayList<DrugItem> drugItemArrayList, Activity activity) {
        this.drugItemArrayList = drugItemArrayList;
       // this.activity = activity;
        this.activity = activity.getActivity();
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
//                Log.v("toast", "adapter에서 테스트");
//                intent = new Intent(view.getContext(), DruginfoActivity.class);
//                view.getContext().startActivity(intent);
                FragmentManager fm = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction;
                DruginfoActivity fragmentDruginfo = new DruginfoActivity();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.menu_frame_layout, fragmentDruginfo);
                fragmentTransaction.commit();

//                intent = new Intent(view.getContext(), DruginfoActivity.class);
//                view.getContext().startActivity(intent);
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

