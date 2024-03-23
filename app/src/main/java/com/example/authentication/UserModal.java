package com.example.authentication;

public class UserModal {
    private String userName;
    private String userMail;
    private String userPwd;
    private int id;

    // creating getter and setter methods
    public String getUserName() { return userName; }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getuserMail()
    {
        return userMail;
    }

    public void setuserMail(String userMail)
    {
        this.userMail = userMail;
    }

    public String getuserPwd() { return userPwd; }

    public void setuserPwd(String userPwd)
    {
        this.userPwd = userPwd;
    }



    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // constructor
    public UserModal(String userName,
                       String userMail,
                       String userPwd)
    {
        this.userName = userName;
        this.userMail = userMail;
        this.userPwd = userPwd;
    }
}

