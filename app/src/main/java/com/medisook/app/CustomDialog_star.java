package com.medisook.app;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

public class CustomDialog_star extends AlertDialog {
    private Context context;

    public CustomDialog_star(Context context){
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_pop);
    }
}
