package com.example.buildingmanagement.login_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.LandingActivity;


public class pickType extends Fragment {


    public pickType() {
        // Required empty public constructor
    }


    public static pickType newInstance(String param1, String param2) {
        pickType fragment = new pickType();
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
        View view = inflater.inflate(R.layout.fragment_pick_type, container, false);


        /**
         * dynamic create a button for a tennant user and board member user.
         * both buttons activate the setType func with the boolean value: true for Board and false
         * for tennant.
         * */
        Button btn = view.findViewById(R.id.btnBoard);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setType( true);
            }
        });

        btn = view.findViewById(R.id.btnTennant);
        btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setType( false);
            }
        }));
        return view;
    }

    // set the flag of the Landing activity with the
    // boolean value  it gets
    public void setType( boolean flag ){
        LandingActivity landing = (LandingActivity) getActivity();
        landing.getUserInfo(flag);
    }
}