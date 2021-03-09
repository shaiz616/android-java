package com.example.buildingmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.login_fragments.Login;
import com.example.buildingmanagement.login_fragments.pickType;
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
    private String path ;

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
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    /**
    * the user is assigned a flag according to the button
    * he choose: true for Board member or false for regular tenant
    * this will determines the way his login info will be authenticated
    * */
    public void getUserInfo(boolean flag) {

        flag1 = flag;
        if(flag1) {
            path = "Board";
        } else {
            path = "Tennant";
        }


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.task, new Login()).addToBackStack(null).commit();

    }
    /**
        a getter for the flag value
     */
    public boolean captureTheFlag() {   return flag1;  }

    /**
        this func will authenticate the user
        based on his login input and his chosen status
        the function will check if the user exist as a
        child of the database in the given path
     */
    public void checkTypeAndLogin(String email, String pw) {
        myRef = myDB.getInstance().getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    if(snap.child("eMail").getValue().equals(email) && snap.child("ps").getValue().equals(pw)) {
                        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                        intent.putExtra("UID", snap.getKey());
                        intent.putExtra("flag", flag1);
                        Toast.makeText(LandingActivity.this, "success", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        break;
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
    }
}