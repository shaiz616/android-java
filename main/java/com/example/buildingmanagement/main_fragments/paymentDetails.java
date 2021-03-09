package com.example.buildingmanagement.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.MainActivity;
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;

import java.util.ArrayList;


public class paymentDetails extends Fragment  {

    private String flatNum;
    private String month;
    private String amount;
    private Payment pay;

    private EditText flat_num;
    private EditText monthlyAmount;
    private EditText editMonth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public paymentDetails() {
        // Required empty public constructor
    }

//    public paymentDetails(ArrayList<Tennant> tennantArrayList)

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment paymentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static paymentDetails newInstance(String param1, String param2) {
        paymentDetails fragment = new paymentDetails();
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
        View view = inflater.inflate(R.layout.fragment_payment_details, container, false);
        Button btn = view.findViewById(R.id.update);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity) getActivity();

                flat_num = view.findViewById(R.id.flatNum);
                flatNum = flat_num.getText().toString() ;

                editMonth = view.findViewById(R.id.month);
                month = editMonth.getText().toString();

                monthlyAmount = view.findViewById(R.id.amount2Pay);
                amount = monthlyAmount.getText().toString();

                if(flatNum != "" && amount !="") {                  // validation that input is not null or empty, then create
                    pay = new Payment(flatNum, amount, month);      // a Payment instance and adds it the the tenent list of payments
                    hasAppartment(pay);

                }else {
                    Toast.makeText(main, "please fill all fields", Toast.LENGTH_LONG).show();
                }


            }
        });
        return  view;
    }


    public void hasAppartment(Payment pay) {
        MainActivity main = (MainActivity) getActivity();
        ArrayList<Tennant> list = main.getList();
        for (Tennant user : list) {
            if(user.getFlat_number() ==Integer.parseInt(pay.getApartment())) {
                main.updateTennantPayment(user, pay);
                break;
            }
        }
    }
}