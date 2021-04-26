package com.example.goodeats.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StaffRepository {
    private StaffDao staffDao;
    private LiveData<List<Staff>> allStaffs;

    public StaffRepository(Application application) {
        StaffDatabase database = StaffDatabase.getInstance(application);
        staffDao = database.staffDao();
        allStaffs = staffDao.getAllStaff();
    }

    public void insert(Staff staff){
        new StaffRepository.InsertStaffAsyncTask(staffDao).execute(staff);
    }
    public void update(Staff staff){
        new StaffRepository.UpdateStaffAsyncTask(staffDao).execute(staff);
    }
    public LiveData<List<Staff>> getAllStaffs(){
        return allStaffs;
    }

    private static class InsertStaffAsyncTask extends AsyncTask<Staff,Void,Void> {
        private StaffDao staffDao;
        private InsertStaffAsyncTask(StaffDao staffDao){
            this.staffDao = staffDao;
        }
        @Override
        protected Void doInBackground(Staff... staffs) {
            staffDao.insert(staffs[0]);
            return null;
        }
    }
    private static class UpdateStaffAsyncTask extends AsyncTask<Staff,Void,Void>{
        private StaffDao staffDao;
        private UpdateStaffAsyncTask(StaffDao staffDao){
            this.staffDao = staffDao;
        }
        @Override
        protected Void doInBackground(Staff... staffs) {
            staffDao.update(staffs[0]);
            return null;
        }
    }
}
