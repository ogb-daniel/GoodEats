package com.example.goodeats.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StaffViewModel extends AndroidViewModel {
    private StaffRepository repository;
    private LiveData<List<Staff>> allStaffs;

    public StaffViewModel(@NonNull Application application) {
        super(application);
        repository = new StaffRepository(application);
        allStaffs = repository.getAllStaffs();
    }

    public void insert(Staff staff){
        repository.insert(staff);
    }

    public void update(Staff staff){
        repository.update(staff);
    }

    public LiveData<List<Staff>> getAllStaffs(){
        return allStaffs;
    }
}
