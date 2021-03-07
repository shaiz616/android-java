package com.example.buildingmanagement.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.buildingmanagement.R;
import com.example.buildingmanagement.frags.Manu;
import com.example.buildingmanagement.frags.MonthlySummary;
import com.example.buildingmanagement.frags.SingleTennatFragment;
import com.example.buildingmanagement.classes.Payment;
import com.example.buildingmanagement.classes.Tennant;
import com.example.buildingmanagement.frags.AllPayment;
import com.example.buildingmanagement.frags.TennantFragment;
import com.example.buildingmanagement.frags.paymentDetails;
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
    private String path = "Tennant", email, msg = "main";
    private Payment payment;
    private Button btn;
    private String userId;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private FirebaseAuth mAuth;
    private FirebaseDatabase myDb;
    private DatabaseReference myRef;
    private FirebaseUser user;
    public static ArrayList<Tennant> tennantslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

//        path = "Board";
        flag = getIntent().getBooleanExtra("flag", true);
        userId = getIntent().getStringExtra("UID");
        Log.d("main 65:", "welcome to main, flag is "+ userId.toString());
        if(flag) {
            myRef = myDb.getInstance().getReference(path);
            openAView(new Manu(), findViewById(R.id.manuFrag));
            getAllTennantData();


        }else {
            myRef = myDb.getInstance().getReference(path);
            openAView(new TennantFragment(), findViewById(R.id.data));
//            path = "Tennant";
            getMyData(path, userId);
        }


//        myRef = myDb.getReference("Tennant");
//        fragmentTransaction.add(R.id.data, new Manu()).addToBackStack(null).commit();

        mAuth = FirebaseAuth.getInstance();
        myDb = FirebaseDatabase.getInstance();
//        getListSize();



    }

    public void onStart() {
        super.onStart();

        FirebaseUser current = mAuth.getCurrentUser();
        String fireID = current.getUid();
        email = current.getEmail();
//        myRef = myDb.getInstance().getReference(path);

        Log.d("***"+msg+"/115", myRef.child(fireID).child("Array").toString() );


    }

    @Override
    public void onBackPressed() {
        Log.d("main/110: ", getFragmentManager().getBackStackEntryCount() + "");

        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public ArrayList<Tennant> getList() {
        return tennantslist;
    }

    public void getMyData(String path, String UserID) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("main/164"," " + snapshot.child(userId).child("eMail")+" " + userId );
                Tennant t = new Tennant();
                t.setFireId(userId);
                t.seteMail(snapshot.child(userId).child("eMail").getValue().toString()); ;
                t.setFirstName(snapshot.child(userId).child("firstName").getValue().toString());
                for(DataSnapshot month : snapshot.child(userId).child("Array").getChildren() ) {
                    Log.d("***171", snapshot.child(userId).child("flat_number").getValue().toString() );
                    payment = new Payment(
                            snapshot.child(userId).child("flat_number").getValue().toString(),
                            month.getValue().toString(),
                            month.getKey()
                    );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void getAllTennantData() {
//        Log.d("@@@", "myref" + myRef);
        btn = (Button) findViewById(R.id.update);

//        DatabaseReference myRefChild = myDb.getReference("Tennant").child("flat_number");
//        myRef = myDb.getReference("Tennant");
        Log.d("***" + msg+"/171", " "+myRef.child(userId).child("eMail"));
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
                    Log.d("****main 149: ", ""+snap.getKey());
//                    t.getPayPerMonth();
                    i=0;
                    for(DataSnapshot month : snap.child("Array").getChildren()) {
                        Log.d("main/199"," " +month.getValue()+"=> i="+i++ );
                        Payment payment = new Payment(
                                snap.child("flat_number").getValue().toString(),
                                month.getValue().toString(),
                                month.getKey()

                        );
                        t.addPayment2List(payment);
                    }
                    tennantslist.add(t);


                    Object obj = snap.getValue(Object.class);
                    Log.d("main 144:","obj: "+ snapshot.getKey() + " , t.mail = " + t.geteMail());
                    String Objkey = snap.getKey();
                    Log.d("main146: val:",""+ snap.getValue());
                    Log.d("main 147: value:","snap: "+ snap.getKey()+", snapshot: "+ snapshot.getKey());
                }
                Log.d("main 149", tennantslist.size() +"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("err", "something went wronf");
            }
        });
    }

    public void selectAView(int viewCall) {
//        Fragment frag = new paymentDetails();
        Log.d("msg1: ", "case 3");
        View targetLayout = findViewById(R.id.linData);

        switch (viewCall){
            case 1:
                openAView(new AllPayment(), targetLayout);

                break;
            case 2:
                openAView(new SingleTennatFragment(), targetLayout);
                break;
            case 3:
                openAView(new paymentDetails(), targetLayout );
                break;
            case 4:
                openAView(new MonthlySummary(), targetLayout);
            default:

        }



    }

    public void openAView(Fragment frag, View target) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(target.getId(), frag).addToBackStack(null).commit();

    }


    public void updateTennantPayment(Tennant user, Payment pay) {

            Log.d("ex2", "found " + user.geteMail());
            myRef = myDb.getReference("Tennant");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    myRef.child(user.getFireId()).child("Array").child(pay.getMonth()).setValue(pay.getPay());
                    Object val = snapshot.getValue(Object.class);
                    Log.d("ex3: ","" +val.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            });
            Log.d("call1:", " " + user.geteMail());
    }
}