package com.example.myapplication;

public class UserInformation {

    public String userName;
    public String userID;
    public String phone;
    public String email;

    public UserInformation() {}

    public UserInformation(String userName, String userID, String phone, String email) {
        this.userName = userName;
        this.userID = userID;
        this.phone = phone;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
