package com.medisook.app;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DruginfoActivity extends Fragment implements View.OnClickListener{
    private String data;
    TextView drugName_view;
    ImageView drugImg_view;
    TextView drugentp_view;
    TextView drugcode_view;
    TextView qnt_view;
    TextView otc_view;
    TextView chart_view;
    TextView efcy_view;
    TextView classname_view;
    TextView usemethod_view;
    TextView qesitm_view;
    TextView term_view;
    TextView deposit_view;
    TextView totalcontent_view;
    TextView mainingr_view;
    TextView ingrname_view;
    ArrayList<DrugItem> drugItem;
    int position;
    private TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_info);

    }

    public void setContentView(int drug_info) {
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (v.getId()){
            case R.id.record_pop_btn:
                CustomDialog_record record_dialog = new CustomDialog_record(getActivity(), position, drugItem);
                CustomDialog_record.Builder record_dialogbulider = new CustomDialog_record.Builder(getActivity());
                record_dialog.setDialogListener(new CustomDialog_record.CustomDialog_record_Listener() {
                    @Override
                    public void onOkClicked(String text) {
                        txt.setText(text);
                    }
                });
                record_dialog.show();
                record_dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                break;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.drug_info, container, false);
        drugName_view = (TextView) rootView.findViewById(R.id.drugName);
        drugImg_view = (ImageView) rootView.findViewById(R.id.drugImage);
        drugentp_view = (TextView) rootView.findViewById(R.id.ENTP_NAME);
        drugcode_view = (TextView) rootView.findViewById(R.id.DRUG_CODE);
        qnt_view = (TextView) rootView.findViewById(R.id.QNT);
        otc_view = (TextView) rootView.findViewById(R.id.OTC);
        chart_view = (TextView) rootView.findViewById(R.id.CHART);
        efcy_view = (TextView) rootView.findViewById(R.id.EFCY);
        classname_view = (TextView) rootView.findViewById(R.id.CLASS_NAME);
        usemethod_view = (TextView) rootView.findViewById(R.id.USEMETHOD);
        qesitm_view = (TextView) rootView.findViewById(R.id.QESITM);
        term_view = (TextView) rootView.findViewById(R.id.VALID_TERM);
        deposit_view = (TextView) rootView.findViewById(R.id.DEPOSIT);
        totalcontent_view = (TextView) rootView.findViewById(R.id.TOTAL_CONTENT);
        mainingr_view = (TextView) rootView.findViewById(R.id.MAIN_INGR);
        ingrname_view = (TextView) rootView.findViewById(R.id.INGR_NAME);
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        drugItem = (ArrayList<DrugItem>) bundle.getSerializable(("DrugItem"));
        String drugName = drugItem.get(position).getDrugName();
        String drugImage  = drugItem.get(position).getDrugImg();
        String entp = drugItem.get(position).getDrugentp();
        String drugcode =drugItem.get(position).getDrugcode();
        String classname =drugItem.get(position).getClassname();
        String qnt = drugItem.get(position).getQnt();
        String otc = drugItem.get(position).getOtc();
        String chart = drugItem.get(position).getChart();
        String efcy =drugItem.get(position).getEfcy();
        String usemethod =drugItem.get(position).getUsemethod();
        String qesitm =drugItem.get(position).getQesitm();
        String deposit = drugItem.get(position).getDeposit();
        String term = drugItem.get(position).getTerm();
        String totalcontent = drugItem.get(position).getTotalcontent();
        String mainingr =drugItem.get(position).getMainingr();
        String ingrname =drugItem.get(position).getIngrname();
        if(drugImage.isEmpty()==false){
            Picasso.get()
                    .load(drugImage)
                    .error(R.drawable.drung_sampleimage)
                    .placeholder(R.drawable.drung_sampleimage)
                    .into(drugImg_view);
        }
        else{
            Drawable drawable= ContextCompat.getDrawable(getContext(), R.drawable.drung_sampleimage);
            drugImg_view.setImageDrawable(drawable);
        }
        Log.d("test", "position : " + position);
        Log.d("test", "drugitem : " + drugItem.size());
        Log.d("test", "drugitem : " + drugItem.get(0));
        drugName_view.setText(drugName);
        drugentp_view.setText(entp);
        drugcode_view.setText(drugcode);
        qnt_view.setText(qnt);
        otc_view.setText(otc);
        chart_view.setText(chart);
        efcy_view.setText(efcy);
        classname_view.setText(classname);
        usemethod_view.setText(usemethod);
        qesitm_view.setText(qesitm);
        term_view.setText(term);
        deposit_view.setText(deposit);
        totalcontent_view.setText(totalcontent);
        mainingr_view.setText(mainingr);
        ingrname_view.setText(ingrname);

        //textview = (TextView) rootView.findViewById(R.id.medisook);
        EditText eText1 = (EditText) rootView.findViewById(R.id.record);
        txt = (TextView)rootView.findViewById(R.id.text);
        //eText1.setOnClickListener(this);
        final Button record_pop_btn = (Button) rootView.findViewById(R.id.record_pop_btn);
        record_pop_btn.setOnClickListener(this);
        return rootView;
    }
}