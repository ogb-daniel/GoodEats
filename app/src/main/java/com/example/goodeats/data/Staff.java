package com.example.goodeats.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "staff_table")
public class Staff {
private String email;
private String username;
public String password;
@PrimaryKey(autoGenerate = true)
private int id = 0;

    public Staff(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
