package com.example.buildingmanagement.classes;

public class Board extends User {
    int seniority;

    public Board( String email, String pw, String fname, String lname, String id, int senior){
        super(fname, lname, id, email, pw);
        this.seniority = senior;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }
}
