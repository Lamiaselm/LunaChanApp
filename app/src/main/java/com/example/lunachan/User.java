package com.example.lunachan;

import android.widget.EditText;

import java.util.Date;

public class User {
    private String mail;
    private String mdp ;
    private String country ;
    private String phone;
    private String full_name;
    private String date_birth;


    public User() {
    }

    public User(String mail, String mdp, String country, String phone, String full_name, String date_birth) {
        this.mail = mail;
        this.mdp = mdp;
        this.country = country;
        this.phone = phone;
        this.full_name = full_name;
        this.date_birth = date_birth;
    }

    public User(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
