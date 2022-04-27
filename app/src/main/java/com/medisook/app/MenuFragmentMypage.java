package com.medisook.app;

import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MenuFragmentMypage extends Fragment {

    private SpannableString content;
    TextView textview;

    public MenuFragmentMypage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_menu_mypage, container, false);
        //textview = (TextView) rootView.findViewById(R.id.medisook);
        return rootView;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_menu_mypage, container, false);
    }
}