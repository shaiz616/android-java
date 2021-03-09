package com.example.buildingmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.main_fragments.Menu;
import com.example.buildingmanagement.main_fragments.MonthlySummary;
import com.example.buildingmanagement.main_fragments.SingleTennatFragment;
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;
import com.example.buildingmanagement.main_fragments.AllPayment;
import com.example.buildingmanagement.main_fragments.TennantFragment;
import com.example.buildingmanagement.main_fragments.paymentDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean flag;
    private String userId, path, email;
    private Payment payment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private FirebaseAuth mAuth;
    private FirebaseDatabase myDb;
    private DatabaseReference myRef;
    public Tennant user = new Tennant();
    private ArrayList<Tennant> tennantslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        myDb = FirebaseDatabase.getInstance();
        fragmentManager = getSupportFragmentManager();

        // users data needed for further communication with the DB
        flag = getIntent().getBooleanExtra("flag", false);
        userId = getIntent().getStringExtra("UID");

        //
        myRef = myDb.getInstance().getReference("Tennant");
        if(flag) {      //if user is Board member, he recives access to option via the menu Fragment that the regular tenant doesn't
            openAView(new Menu(), findViewById(R.id.manuFrag));
            getAllTennantData();
        }else {
            getMyData();
            openAView(new TennantFragment(), findViewById(R.id.data));
        }
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public ArrayList<Tennant> getList() {   return tennantslist;    }

    public Tennant getUser() {      return user;    }


    //      fetch Data (as a 'simple' user - a tenant) for single loged-in user
    public void getMyData() {
        myRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.setFireId(userId);
                user.seteMail(snapshot.child("eMail").getValue().toString());
                user.setFirstName(snapshot.child("firstName").getValue().toString());
                user.setFlat_number(Integer.parseInt(snapshot.child("flat_number").getValue().toString()));
                for(DataSnapshot month : snapshot.child("Array").getChildren() ) {
                    payment = new Payment(
                            snapshot.child("flat_number").getValue().toString(),
                            month.getValue().toString(),
                            month.getKey()
                    );
                    user.addPayment2List(payment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

        //      fetch Data (as a Board member) for all tenant
    public void getAllTennantData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tennantslist = new ArrayList<>();
                int i;
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Tennant t = new Tennant();
                    t.seteMail(snap.child("eMail").getValue().toString());
                    t.setFlat_number(Integer.parseInt(snap.child("flat_number").getValue().toString()));
                    t.setFirstName(snap.child("firstName").getValue().toString());
                    t.setFireId(snap.getKey());
                    for(DataSnapshot month : snap.child("Array").getChildren()) {
                        payment = new Payment(
                                snap.child("flat_number").getValue().toString(),
                                month.getValue().toString(),
                                month.getKey()
                        );
                        t.addPayment2List(payment);
                    }
                    tennantslist.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //      open a specific Fragment according to the button clicked by the user
    public void selectAView(int viewCall) {
        View targetLayout = findViewById(R.id.linData);

        switch (viewCall){
            case 1:
                openAView(new AllPayment(), targetLayout);
                break;
            case 2:
                openAView(new SingleTennatFragment(), targetLayout);
                break;
            case 3:
                openAView(new paymentDetails(), targetLayout);
                break;
            case 4:
                openAView(new MonthlySummary(), targetLayout);
        }
    }


    public void openAView(Fragment frag, @NonNull View target) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(target.getId(), frag).addToBackStack(null).commit();
    }


    /**
     * this function get a payment instance and the tennant instance the payment belongs to
     * and update the firebase user with the payment details
     *
     */
    public void updateTennantPayment(Tennant user, Payment pay) {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    myRef.child(user.getFireId()).child("Array").child(pay.getMonth()).setValue(pay.getPay());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            });
    }
}