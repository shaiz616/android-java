package com.example.buildingmanagement.main_fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
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
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TennantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TennantFragment extends Fragment {

    public TennantFragment() {
        // Required empty public constructor
    }


    public static TennantFragment newInstance() {
        TennantFragment fragment = new TennantFragment();

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

        View view = inflater.inflate(R.layout.fragment_tennant, container, false);
        TextView txt = view.findViewById(R.id.welcome);
        txt.setText("welcome");
        txt.setTextColor(Color.RED);
        Button btn = view.findViewById(R.id.my_payment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayMyPayments(view);
            }
        });
        return view;
    }

    public void displayMyPayments(View view) {
        MainActivity main = (MainActivity) getActivity();
        TextView txtV = new TextView(getActivity());
        String str = "my payments: \n";
        for (Payment pay : main.user.getPayed_months()){
            str += Integer.parseInt(pay.getMonth())+1 +": " + pay.getPay() + "\n";
        }
            txtV.setText(str);
            LinearLayout mylyout = view.findViewById(R.id.myPayment2);
            if(mylyout.getChildCount()>1){
                mylyout.removeViewAt(1);
            }
            mylyout.addView(txtV, 1);
    }
}