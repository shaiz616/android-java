package com.example.buildingmanagement.main_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPayment extends Fragment {

    MainActivity main ;
    ArrayList<Tennant> list ;
    LinearLayout.LayoutParams myParam;


    public AllPayment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllPayment newInstance() {
        AllPayment fragment = new AllPayment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main=  (MainActivity) getActivity();
        list = main.getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_payment, container, false);
        String text2display;
        TextView txt;
        myParam =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int i = 0;
        LinearLayout innerView;
        LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.summeryLayout);

        for(Tennant user: list){
            innerView = new LinearLayout(getActivity());
            innerView.setOrientation(LinearLayout.VERTICAL);
            txt =new TextView(getActivity());
            txt.setText(user.data2String(2));
            txt.setBackgroundColor(Color.argb(200, 3, 218, 198));
            txt.setTextScaleX(1.2f);
            txt.setLayoutParams(myParam);
            innerView.addView(txt);

//            text2display = "";
            txt =new TextView(getActivity());

            text2display = user.data2String(1);

            txt.setText(text2display);
            txt.setTextScaleX(1.2f);
            txt.setBackgroundColor(Color.parseColor("#66F9B0"));
            txt.setAlpha(0.8f);
            txt.setTextScaleX(1.2f);

            txt.setLayoutParams(myParam);
            innerView.addView(txt);
            viewLayout.addView(innerView, i++);
        }
        return view;
    }
}