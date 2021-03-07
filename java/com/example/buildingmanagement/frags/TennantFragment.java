package com.example.buildingmanagement.frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Tennant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TennantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TennantFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TennantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TennantManu.
     */
    // TODO: Rename and change types and number of parameters
    public static TennantFragment newInstance(String param1, String param2) {
        TennantFragment fragment = new TennantFragment();
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
        View view = inflater.inflate(R.layout.fragment_tennant, container, false);
        Button btn = view.findViewById(R.id.my_payment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPayment(view);
            }
        });




        return view;
    }

    public void showMyPayment(View view) {
        MainActivity main = (MainActivity) getActivity();
        Tennant t = main.getuser();
        TextView txt = new TextView(getActivity());
        if (t != null) {
            txt.setText(t.data2String(1));
            RelativeLayout myLyout = view.findViewById(R.id.myPayment2);
            myLyout.addView(txt ,1);
        }else{
            Log.d("msg1", "fail");
        }
    }
}