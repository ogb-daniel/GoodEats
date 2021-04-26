package com.example.goodeats.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
private String name;
private String emailId;
private int contactNum;
private String username;
private String password;
@PrimaryKey(autoGenerate = true)
private int id = 0;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String emailId, int contactNum, String username, String password) {
        this.name = name;
        this.emailId = emailId;
        this.contactNum = contactNum;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public int getContactNum() {
        return contactNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
