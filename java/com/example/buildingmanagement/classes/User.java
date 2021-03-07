package com.example.buildingmanagement.classes;

public class User {

    private String firstName;
    private String surName;
    private String id;
    private String password;
    private String eMail;

    public User() {}

    public User(String name, String lname, String id, String mail, String pw) {
        this.firstName = name;
        this.surName = lname;
        this.id = id;
        this.eMail = mail;
        this.password = pw;
    }



    public String getFirstName() {      return firstName;    }

    public void setFirstName(String fname) {
        this.firstName = fname;
    }



    public String getSurName() {    return surName;     }

    public void setSurName(String surName) {
        this.surName = surName;
    }



    public String getId() {         return id;      }

    public void setId(String id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPs() {         return password;      }

    public void setPs(String ps) {
        this.password = ps;
    }
}
