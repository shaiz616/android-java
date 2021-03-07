package com.example.buildingmanagement.classes;

public class Payment {
    private String apartment;
    private String pay;
    private String month;

    public Payment(String flat, String pay, String month) {
        this.apartment = flat;
        this.pay = pay;
        this.month = month;
    }

    public String getApartment() {
        return apartment;
    }

    public String getPay() {
        return pay;
    }

    public String getMonth() {
        return month;
    }
}
