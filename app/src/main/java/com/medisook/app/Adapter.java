package com.medisook.app;
// https://3001ssw.tistory.com/201
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
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

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<DrugItem> drugItemArrayList;
    Activity activity;

    public Adapter(ArrayList<DrugItem> drugItemArrayList, MenuFragmentSearch activity) {
        this.drugItemArrayList = drugItemArrayList;
        this.activity = activity.getActivity();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drug_list,parent, false);
        ViewHolder viewholder = new ViewHolder(context, view);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrugItem drugimage = drugItemArrayList.get(position);
        String imageurl=drugimage.getDrugImg();
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

        holder.drugName.setText(drugItemArrayList.get(position).getDrugName());
            holder.drugName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager fm = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction;
                DruginfoActivity fragmentDruginfo = new DruginfoActivity();
                DrugItem drugItem = new DrugItem();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DrugItem", drugItemArrayList);
                bundle.putInt("position", position);
                fragmentDruginfo.setArguments(bundle);
                fragmentTransaction = fm.beginTransaction().add(R.id.menu_frame_layout, fragmentDruginfo);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        return drugItemArrayList.size();
    }
    public void setArrayData(DrugItem strData) {
        drugItemArrayList.add(strData);
    }
}


