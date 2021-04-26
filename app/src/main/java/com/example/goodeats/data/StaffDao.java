package com.example.goodeats.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StaffDao {
    @Insert
    void insert(Staff staff);

    @Update
    void update(Staff staff);

    @Query("SELECT * FROM staff_table")
    LiveData<List<Staff>> getAllStaff();
}
