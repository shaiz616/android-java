package com.example.buildingmanagement.main_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;


public class MonthlySummary extends Fragment {

    ArrayList<Tennant> list ;
    MainActivity main ;

    // TODO: Rename and change types of parameters
    private String mParam1 = "monthly Summ" ;
    private String mParam2;

    public MonthlySummary() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MonthlySummary newInstance() {
        MonthlySummary fragment = new MonthlySummary();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.frag_monthly_summary, container, false);
        main = (MainActivity) getActivity();
        list = main.getList();

        LinearLayout myLayout = view.findViewById(R.id.summery);
        myLayout.addView(viewIncome(), 0);
        return view;
    }

    public int[] calculateIncome() {
        int[] incomePerMonth = new int[12];

        for(Tennant user : list ){
            for(Payment pay : user.getPayed_months()){
                incomePerMonth[Integer.parseInt(pay.getMonth())] += Integer.parseInt(pay.getPay());
            }
        }
        return incomePerMonth;
    }

    public String income2String(int[] income) {

        String myTxt = "";
        for(int i=0; i< income.length ; i++) {
            myTxt +=  "   "+(i+1) +": " + income[i] + "|  ";
            if((i+1)%3==0)
                myTxt+="\n";

        }
        return myTxt;
    }

    public TextView viewIncome() {
        TextView txt = new TextView(getActivity());
        txt.setLines(4);
        txt.setBackgroundColor(Color.parseColor("#66F9B0"));

        txt.setText(income2String(calculateIncome()));

        return txt;
    }
}