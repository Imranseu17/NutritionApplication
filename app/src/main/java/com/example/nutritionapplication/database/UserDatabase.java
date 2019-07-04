package com.example.nutritionapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.nutritionapplication.dao.UserDao;
import com.example.nutritionapplication.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
