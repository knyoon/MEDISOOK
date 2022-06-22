package com.medisook.app;
// https://3001ssw.tistory.com/201
import static com.medisook.app.MenuFragmentSearch.IP_ADDRESS;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_wishlist extends RecyclerView.Adapter<ViewHolder_wishlist> implements OnItemClickListener{
    private ArrayList<String> wishlistItemArrayList;
    OnItemClickListener listener;
    MenuFragmentSearch mf = new MenuFragmentSearch();
    CustomDialog_wishlist dialog;
    public Adapter_wishlist(ArrayList<String> wishlistItemArrayList, CustomDialog_wishlist dialog) {
        this.dialog = dialog;
        this.wishlistItemArrayList = wishlistItemArrayList;
    }

    @NonNull
    public ViewHolder_wishlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout. wish_list, parent, false);
        com.medisook.app.ViewHolder_wishlist viewholder_wishlist = new com.medisook.app.ViewHolder_wishlist(view, this);
        return viewholder_wishlist;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_wishlist holder, int position) {
        final int pos = position;
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                wishlistItemArrayList.remove(position);
//                Log.d("list", "삭제 되는지");
//                notifyDataSetChanged();
//                return false;
//            }
//        });
        Log.d("위시리스트", wishlistItemArrayList.get(pos));
        holder.txt.setText(wishlistItemArrayList.get(pos)); //삭제해도 되나?
        holder.txt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
        holder.txt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MenuFragmentSearch.InsertData insert = mf.new InsertData();
                insert.execute("http://" + IP_ADDRESS + "/delete_wish.php", "2", holder.txt.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
                return false;
            }
        });
    }

    public int getItemCount() {
        return wishlistItemArrayList.size();
    }
    public void setArrayData(String strData){
        wishlistItemArrayList.add(strData);
    }
    public void setOnItemClicklistener(OnItemClickListener listener) {this.listener = listener;}
    @Override
    public void onItemClick(ViewHolder_filter holder, View view, int position) {

    }
//    public void filterList(ArrayList<FilterItem> filteredList){
//        filterItemArrayList = filteredList;
//        notifyDataSetChanged();
//    }
}