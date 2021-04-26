package com.example.goodeats.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Staff.class},version = 1)
public abstract class StaffDatabase extends RoomDatabase {
    private static StaffDatabase instance;
    public abstract StaffDao staffDao();

    public static synchronized StaffDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StaffDatabase.class,"staff_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new StaffDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private StaffDao staffDao;

        private PopulateDbAsyncTask(StaffDatabase db){
            staffDao = db.staffDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            staffDao.insert(new Staff("admin@gmail.com","admin","admin"));
            return null;
        }
    }
}
