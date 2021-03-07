package com.example.buildingmanagement.frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.LandingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pickType#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pickType extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pickType() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pickType.
     */
    // TODO: Rename and change types and number of parameters
    public static pickType newInstance(String param1, String param2) {
        pickType fragment = new pickType();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_type, container, false);
        boolean flag = false;

        Button btn = view.findViewById(R.id.btnBoard);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag:", "in type: true");
                setType( true);
            }
        });
        btn = view.findViewById(R.id.btnTennant);
        btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag:", "in type: false");
                setType( false);
            }
        }));

        return view;


    }

    public void setType( boolean flag ){
        Log.d("tag: ", "in type flag is " + flag);

        LandingActivity landing = (com.example.buildingmanagement.activities.LandingActivity) getActivity();

        landing.getUserInfo(flag);

    }
}