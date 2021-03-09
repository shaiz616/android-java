package com.example.buildingmanagement.login_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.activities.LandingActivity;
import com.example.buildingmanagement.classes.Tennant;
import com.example.buildingmanagement.classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends Fragment {

    private boolean flag;
    private String path;

    private EditText emailView;
    private EditText pwView;
    private LandingActivity landing ;

    private FirebaseAuth mAuth;
    private FirebaseUser current;
    private FirebaseDatabase myDB;
    private DatabaseReference myRef;




    private String email;
    private String password;

    public Login() {
        // Required empty public constructor
    }


    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }

    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        landing = (LandingActivity) getActivity();
        flag = landing.captureTheFlag();

        //determine the path needed to authenticate the user according to his
        // chosen title
        path = (flag)? "Board" : "Tennant";

        Button btn = view.findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailView = view.findViewById(R.id.editEmail) ;
                String email = emailView.getText().toString();

                pwView = view.findViewById(R.id.editPW);
                String pw = pwView.getText().toString();


                if(!email.isEmpty() && !pw.isEmpty()) {
                    Toast.makeText(getActivity() , "hello " + email, Toast.LENGTH_LONG);
                    LandingActivity landing = (com.example.buildingmanagement.activities.LandingActivity) getActivity();
                    landing.checkTypeAndLogin(email, pw);
                }
            }
        });
        btn = view.findViewById(R.id.btnNewPW);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields(view, path);
            }
        });
        return  view;
    }

    public void resetFields(View view, String path) {
        Toast.makeText(getContext(), "please rewrite your e-mail and your new password", Toast.LENGTH_LONG).show();
        emailView = view.findViewById(R.id.editEmail);
        emailView.setText("");
        pwView = view.findViewById(R.id.editPW);
        pwView.setText("");
        LinearLayout myLyout = view.findViewById(R.id.loginLyout);
        Button btn = view.findViewById(R.id.btnLogin);
        myLyout.removeView(btn);
        btn = view.findViewById(R.id.btnNewPW);
        btn.setText("Submit  new Password");
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changePassword(view, path);
            }
        });
    }

    public void changePassword(View view, String path) {
        emailView = view.findViewById(R.id.editEmail);
        email = emailView.getText().toString();

        pwView = view.findViewById(R.id.editPW);
        password = pwView.getText().toString();

        myDB = FirebaseDatabase.getInstance();
        myRef = myDB.getReference(path);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren() ) {
                    if(snap.child("eMail").getValue().toString().equals(email) ) {
                        myRef.child(snap.getKey()).child("ps").setValue(password);
                        mAuth = FirebaseAuth.getInstance();
                        current = mAuth.getCurrentUser();
                        landing.checkTypeAndLogin(email, password);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}