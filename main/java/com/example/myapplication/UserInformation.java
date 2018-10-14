package com.example.myapplication;

public class UserInformation {

    public String userName;
    public String userID;
    public String phone;

    public UserInformation() {}

    public UserInformation(String userName, String userID, String phone) {
        this.userName = userName;
        this.userID = userID;
        this.phone = phone;
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
}
