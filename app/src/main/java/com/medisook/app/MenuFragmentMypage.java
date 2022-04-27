package com.medisook.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuFragmentMypage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_menu_mypage, container, false);
        return rootView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_menu_mypage, container, false);
    }
}