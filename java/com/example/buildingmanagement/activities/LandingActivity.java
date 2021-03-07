package com.example.buildingmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.classes.Tennant;
import com.example.buildingmanagement.classes.User;
import com.example.buildingmanagement.frags.Login;
import com.example.buildingmanagement.frags.pickType;
import com.example.buildingmanagement.frags.register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LandingActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private boolean flag1;
    private String path, msg = "landing/";


    private FirebaseAuth mAuth;
    private FirebaseDatabase myDB;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.task, new pickType()).addToBackStack(null).commit();
        mAuth = FirebaseAuth.getInstance();
        flag1 = false;
        path = "Tennant";
    }

    public  void onStart() {
        super.onStart();
        FirebaseUser current =  mAuth.getCurrentUser();
    }

    @Override
    public void onBackPressed() {
        Log.d("landing/63: ", getFragmentManager().getBackStackEntryCount() + "");
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    public void getUserInfo(boolean flag) {

        Bundle data = new Bundle();
        flag1 = flag;
        data.putBoolean("data", flag1);
        if(flag1) {
            path = "Board";
        } else {
            path = "Tennant";
        }


        fragmentManager = getSupportFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.task, new Login()).addToBackStack(null).commit();

    }

    public void checkLoginType(String email, String pw) {
        myRef = myDB.getInstance().getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    if(snap.child("eMail").getValue().equals(email) && snap.child("ps").getValue().equals(pw)) {

                        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                        intent.putExtra("UID", snap.getKey());
                        intent.putExtra("flag", flag1);
                        startActivity(intent);
                        login(email, pw);
                    }else{
                        continue;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LandingActivity.this, "communication error \nplease try again", Toast.LENGTH_LONG).show();

            }
        });
        Toast.makeText(LandingActivity.this, "wrong login info", Toast.LENGTH_LONG).show();

    }

    public void login(String email, String pw ){
        Log.d("+++", "msg: flag is" + flag1 + "my name is " + email);




        mAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LandingActivity.this, "login ok", Toast.LENGTH_SHORT).show();

                            FirebaseUser user1 = mAuth.getCurrentUser();
                            Log.d(msg+"137"," "+ user1);



//                            Log.d("err 0", "main");
//                            myRef = myDB.getReference("Tennant");
//                            myRef.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    for(DataSnapshot snap : snapshot.getChildren() ) {
//                                        Tennant t = new Tennant();
//                                        t.seteMail(snap.child("eMail").getValue().toString());
//                                        t.setFlat_number(Integer.parseInt(snap.child("flat_number").getValue().toString()));
//                                        t.setFirstName(snap.getValue().toString());
//                                        MainActivity.tennantslist.add(t);
//
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                }
//                            });


                        }else{
                            Log.w( "###", "signin fail, ", task.getException() );

                        }
                    }
                }
        );

    }

    public void openRegisterView() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.task, new register()).addToBackStack("login").commit();
        register.setFlag(flag1);
    }

    public void createNewAccount(User user) {


        mAuth.createUserWithEmailAndPassword(user.geteMail(), user.getPs()).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LandingActivity.this, "regis ok", Toast.LENGTH_SHORT).show();
                            FirebaseUser u1 = mAuth.getCurrentUser();
                            String uid = u1.getUid();
                            Log.d(this.toString(), "&&&     success     &&&");

                            myDB = FirebaseDatabase.getInstance();
                            myRef = myDB.getReference(path).child(uid);
                            myRef.setValue(user);
                            login(user.geteMail(), user.getPs());

                        }else{
                            Toast.makeText(LandingActivity.this , "regis failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}