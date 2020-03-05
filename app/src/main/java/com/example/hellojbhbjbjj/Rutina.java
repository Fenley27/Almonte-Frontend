package com.example.hellojbhbjbjj;

public class Rutina {

    String firstname;
    String lastname;
    String identification;
    String id;
    String amount;
    String phone;
    String adress;
    String payStatus;
    String status;
    String date;

    public Rutina(String firstname, String lastname,
                  String identification, String id, String amount,String phone,String adress,
                  String payStatus, String status, String date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.identification = identification;
        this.id = id;
        this.amount = amount;
        this.phone = phone;
        this.adress = adress;
        this.payStatus = payStatus;
        this.status = status;
        this.date = date;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getIdentification() {
        return identification;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getPhone(){
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
