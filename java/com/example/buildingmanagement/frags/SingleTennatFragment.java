package com.example.buildingmanagement.frags;

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
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleTennatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleTennatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Tennant> list ;
    MainActivity main ;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingleTennatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleTennatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleTennatFragment newInstance(String param1, String param2) {
        SingleTennatFragment fragment = new SingleTennatFragment();
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
        main=  (MainActivity) getActivity();
        list = main.getList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_single_tennat, container, false);
        Button btn;
        LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.subView);
//        ArrayList<Payment> payedList;
//        int i = 1;
        for (Tennant user : list) {
            btn = new Button(getActivity());
            btn.setText("flat number " + user.getFlat_number());
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

    public void getTennantPayments(int flatNum,View view) {

        LinearLayout viewLayout = (LinearLayout) view.findViewById(R.id.linView);
        for (Tennant u1 : list) {
            if(flatNum == u1.getFlat_number()) {
//                viewLayout.removeViewAt(0);
                viewLayout.addView(returnTennatPayments(u1),0);
            }
        }
    }


    public TextView returnTennatPayments(Tennant user) {
        TextView txtV = new TextView(getActivity());
        txtV.setText(user.getFlat_number()+": "+user.data2String(1));
        return txtV;

    }
}