package com.example.buildingmanagement.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static ArrayList<Tennant> list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public Menu() {
        // Required empty public constructor
    }

//    public static void addUser2List(Tennant t) {
//        list.add(t);
//    }
//
//    public static Tennant getFirstUser() {
//        return list.get(0);
//    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
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
        MainActivity main = (MainActivity) getActivity();
        View  view = inflater.inflate(R.layout.fragment_manu, container, false);
        Button btn = view.findViewById(R.id.allPayments);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.selectAView(1);
            }
        });

        Button btn1 = view.findViewById(R.id.singlePayment);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("manu:", " 1");
//                Log.d("new", " " + MainActivity.tennantslist.get(0).geteMail());
                main.selectAView(2);
            }
        });

        Button btn2 = view.findViewById(R.id.enterPay);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getDetails(view);
                main.selectAView(3);
            }
        });

        btn = view.findViewById(R.id.btnSummry);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.selectAView(4);

            }
        });

        return view;
    }



    public void getDetails(View view) {
    }
}