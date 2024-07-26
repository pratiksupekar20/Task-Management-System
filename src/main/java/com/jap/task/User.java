package com.jap.task;

public class User {


    //declare the attribute userName,password
    private String userName;
    private String password;

    public User(){

    }
    public User(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }
  //declare the getter setter and toString methods

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "username "+getUserName()+" password: "+getPassword();
    }
}
