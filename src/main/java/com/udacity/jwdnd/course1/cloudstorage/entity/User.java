package com.udacity.jwdnd.course1.cloudstorage.entity;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String salt;
    private String firstName;
    private String lastName;

    public User(Integer userId, String username, String password, String salt, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
