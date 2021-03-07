package com.example.buildingmanagement.classes;

import android.util.Log;

import java.util.ArrayList;

public class Tennant extends User {

    private int flat_number;
//    private int flat_size;
    private int monthlyPayment;
    private ArrayList<Payment> payed_months;
    String fireId;

    public Tennant() {
        this.monthlyPayment = 500;
        this.payed_months = new ArrayList<>();
    }


    public Tennant( String email,  String pw, String name, String famName, String id, int flatNom, ArrayList<Payment> payed ){

        super(name, famName, id, email, pw);
        this.flat_number = flatNom;
        this.payed_months = payed;
    }

    public Tennant( String email, String pw, String name, String famName, String id, int flatNum){
        super(name, famName, id, email, pw);
        this.flat_number = flatNum;
        this.monthlyPayment = 500;
        this.payed_months = new ArrayList<>();
    }


    public void setFlat_number(int num) {
        this.flat_number = num;
    }

    public int getFlat_number() {   return this.flat_number;   }


//    private void setFlat_size(int size) {
//        this.flat_size = size;
//    }
//
//    public int getFlat_size()  {   return this.flat_size; }

    private void setPayPerMonth(int sum) {
        this.monthlyPayment = sum;
    }

    public int getPayPerMonth(){    return this.monthlyPayment; }


//    public void setPayed_months(ArrayList<Integer> payed_months) {
//        this.payed_months = payed_months;
//    }

    public ArrayList<Payment> getPayed_months() {
        return payed_months;
    }

    public String getFireId() {
        return fireId;
    }


    public void setFireId(String fireId) { this.fireId = fireId; }

    public String data2String(int flag) {
        String str="";
        if(flag==1) {
            for (Payment pay : payed_months) {
                str += pay.getMonth() + ", ";
            }
        }else {
            str+= "Apartment number "+ this.flat_number + ": "+getFirstName();
        }
        return str;
    }


    public void addPayment2List(Payment pay) {
        payed_months.add(pay);
    }

}
