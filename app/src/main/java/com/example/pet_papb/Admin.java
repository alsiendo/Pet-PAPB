package com.example.pet_papb;

public class Admin {

    public String userName, email, password, imageURL, date, gender, address;

    public Admin(){

    }

    public Admin(String userName, String email, String passWord, String imageURL, String date, String gender, String address){
        this.userName = userName;
        this.email = email;
        this.password = passWord;
        this.imageURL = "default";
        this.date = "default";
        this.gender = "default";
        this.address = "default";
    }

}
