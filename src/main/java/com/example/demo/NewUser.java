package com.example.demo;

public class NewUser {
    private String username;
    private int age;
    private String password;
    private String repassword;

    public NewUser(String username, String password, String repassword, int age) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.repassword = repassword;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }
}
