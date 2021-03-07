package com.example.buildingmanagement.frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthlySummary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthlySummary extends Fragment {

    ArrayList<Tennant> list ;
    MainActivity main ;

    // TODO: Rename and change types of parameters
    private String mParam1 = "monthly Summ" ;
    private String mParam2;

    public MonthlySummary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthlySummary.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthlySummary newInstance(String param1, String param2) {
        MonthlySummary fragment = new MonthlySummary();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        main = (MainActivity) getActivity();
        list = main.getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.frag_monthly_summary, container, false);

        int[] income = calculateIncome();
        String myTxt = "";

        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.summery);
//        TextView txt;


//        txt = new TextView(sActivity());
//        txt.setText(income2String(income));
//        Button btn = new Button(getActivity());
//        btn.setText(income2String(calculateIncome()));
//        myLayout.addView(btn);

        myLayout.addView(viewIncome(), 0);


//        Log.d(mParam1+"/88", myTxt);

        return view;
    }

    public int[] calculateIncome() {

        int[] incomePerMonth = new int[12];
        ArrayList<Payment> payed;
        Log.d(mParam1 +"/81", " " + incomePerMonth[5]);

        for(Tennant user : list ){
            payed = user.getPayed_months();
            for(Payment pay : payed){
                incomePerMonth[Integer.parseInt(pay.getMonth())] += Integer.parseInt(pay.getPay());
            }
//            Log.d(mParam1 +"/79", user.getFirstName());

        }

        return incomePerMonth;
//        Log.d(mParam1+"/91", " "+ incomePerMonth[3]);
//        displayIncome(incomePerMonth, view);
    }

    public String income2String(int[] income) {

        String myTxt = "";
        for(int i=0; i<income.length ; i++) {
            myTxt +=  "   "+(i+1) +": " + income[i] + "|  ";
            if((i+1)%3==0)
                myTxt+="\n";

        }
        Log.d(mParam1 + "/120", ""+myTxt);

        return myTxt;
    }

    public TextView viewIncome() {
        TextView txt = new TextView(getActivity());
        txt.setLines(4);

        txt.setText(income2String(calculateIncome()));

        return txt;
    }
}