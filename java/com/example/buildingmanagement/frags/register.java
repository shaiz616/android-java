package com.example.buildingmanagement.frags;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.LandingActivity;
import com.example.buildingmanagement.classes.Board;
import com.example.buildingmanagement.classes.Tennant;
import com.example.buildingmanagement.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Boolean.getBoolean;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class register extends Fragment {


    private FirebaseAuth mAuth;
    private FirebaseDatabase myDB;
    private DatabaseReference myRef;
    private Fragment frags;


    private EditText input;
    private static boolean flag = false;
//    EditText fName;
//    EditText surName;
//    EditText Id;
//    EditText flatNum;
//    EditText
//    EditText
//    EditText



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public register() {
        // Required empty public constructor
    }

    public register(boolean flag) {
        this.flag = flag;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment tennatRegis.
     */
    // TODO: Rename and change types and number of parameters
    public static register newInstance() {
        register fragment = new register();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    public static void setFlag(boolean fl) {
        register.flag = fl;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        String hint;
        String textId;
        if(flag){
            hint = "seniority";
//            textId = "editVetek";
        }else{
            hint = "House Number";
//            textId = "houseNum";
        }

        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.subLayout);

        EditText editText = new EditText(getActivity());
        editText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        editText.setHint(hint);
        editText.setHintTextColor(Color.GRAY);
        int ele_id = ViewCompat.generateViewId();
        editText.setId(ele_id);
        myLayout.addView(editText, 0);

        Log.d("msg: ", "flag is**** " + flag);


        Button btn = view.findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tag:", "in rege: true");
                getNewTennatInfo(view);
            }
        });

        return view;
    }

    public  void onStart() {
        super.onStart();
        FirebaseUser current = mAuth.getCurrentUser();

    }

    public void getNewTennatInfo(View view) {

        input = view.findViewById(R.id.regisTennantEmail);
        String email =input.getText().toString();

        input = view.findViewById(R.id.editName);
        String fname = input.getText().toString();

        input = view.findViewById(R.id.editLastName);
        String lName  = input.getText().toString();

        input = view.findViewById(R.id.editId);
        String id = input.getText().toString();

        int child = ((ViewGroup) view.findViewById(R.id.subLayout) ).getChildAt(0).getId();
        input = view.findViewById(child);
        int num = Integer.parseInt(input.getText().toString()) ;




        input = view.findViewById(R.id.editPassword);
        String pw = input.getText().toString();

        User member = new User();
        if(flag) {
            member = new Board(email, pw, fname, lName, id, num );
        }else {
            member = new Tennant(email, pw, fname, lName, id, num);
        }


        LandingActivity landing = (com.example.buildingmanagement.activities.LandingActivity) getActivity();
        landing.createNewAccount(member);

//        mAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getActivity(), "regis ok", Toast.LENGTH_SHORT).show();
//                    FirebaseUser u1 = mAuth.getCurrentUser();
//                    String uid = u1.getUid();
//                    Log.d(getTag(), "&&&  success   &&&");
//                    Tennant t1 = new Tennant(fname, fname, id,flatNum, pw );
//
//                    myDB = FirebaseDatabase.getInstance();
//                    myRef = myDB.getReference("Tennat").child(uid);
//                    myRef.setValue(t);
//
//
//                }else {
//                    Toast.makeText(getActivity(), "regis failed", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

    }


}