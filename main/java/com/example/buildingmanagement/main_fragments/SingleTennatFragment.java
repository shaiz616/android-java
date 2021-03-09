package com.example.buildingmanagement.main_fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;


public class SingleTennatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ArrayList<Tennant> list ;
    private MainActivity main ;
    private LinearLayout.LayoutParams myParam;
    private ViewGroup.MarginLayoutParams margParms;

    public SingleTennatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SingleTennatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleTennatFragment newInstance() {
        SingleTennatFragment fragment = new SingleTennatFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * when this fragment is created, it dynamically create a button
     * for every tenenant with onClick listner.
     * the onClick func will activate a series of functions that will
     * display the desired information
     * */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_single_tennat, container, false);
        Button btn;
        main = (MainActivity) getActivity();
        myParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.subView);
        viewLayout.setGravity(2);
        for (Tennant user : main.getList()) {
            btn = new Button(getActivity());
            btn.setText("flat number " + user.getFlat_number());
            btn.setBackgroundColor(Color.parseColor("#33B5E5"));

            btn.setAlpha(0.8f);
            btn.setLayoutParams(myParam);
            btn.offsetTopAndBottom(3);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTennantPayments(user.getFlat_number(), view);
                }
            });
            viewLayout.addView(btn);
        }
        return view;
    }

    /**
     *
     * this is the function that's triggered by the onClick.
     * when it's called, it will compare the first arg it recives againt the element of tennentlist
     * that it fetches from MainActivity, and when it find a match
     * sends it the next function
     * */
    public void getTennantPayments(int flatNum,View view) {

        list = main.getList();
        LinearLayout viewLayout = view.findViewById(R.id.linView);
        if(viewLayout.getChildCount()>2) {
            viewLayout.removeViewAt(1);
        }

        for (Tennant u1 : list) {
            if(flatNum == u1.getFlat_number()) {
                viewLayout.addView(returnTennatPayments(u1),1);
            }
        }
    }

    /**
     * this func create a textView with a string it gets from
     *  the instance of the tenant we want to display
     *  and retruns it to the privios func that add it to the view
     *
    * */
    public TextView returnTennatPayments(Tennant user) {
        TextView txtV = new TextView(getActivity());
        txtV.setText(" "+user.getFlat_number()+":\n "+user.data2String(1)+" ");
        txtV.setLayoutParams(myParam);
        txtV.setPadding(5, 5 , 0, 0);
        txtV.setScaleX(2f);
        txtV.setBackgroundColor(Color.parseColor("#66F9B0"));
        txtV.setAlpha(0.8f);

        return txtV;

    }
}