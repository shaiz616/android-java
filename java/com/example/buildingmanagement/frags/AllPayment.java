package com.example.buildingmanagement.frags;

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
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllPayment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllPayment extends Fragment {

    MainActivity main ;
    ArrayList<Tennant> list ;


    public AllPayment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllPayment newInstance(String param1, String param2) {
        AllPayment fragment = new AllPayment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        main=  (MainActivity) getActivity();
        list = main.getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_payment, container, false);
        String text2display;
//        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextView txt;

        int layoutId, i = 0, j = 0;
        LinearLayout innerView;
        LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.summeryLayout);

        for(Tennant user: list){
            innerView = new LinearLayout(getActivity());
            innerView.setOrientation(LinearLayout.VERTICAL);
            txt =new EditText(getActivity());
            txt.setText(user.data2String(2));
            innerView.addView(txt);
//            innerView = new LinearLayout(getActivity());
//            innerView.setOrientation(LinearLayout.HORIZONTAL);
            text2display = "";
            txt =new EditText(getActivity());

            text2display = user.data2String(1);
            Log.d("allp/86", "try");
//            for(Payment month : user.getPayed_months()) {
//                text2display += month.getMonth()+", ";
//            }
            txt.setText(text2display);
            innerView.addView(txt);
            viewLayout.addView(innerView, i++);


        }

        return view;
    }
}