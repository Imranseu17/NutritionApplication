package com.example.nutritionapplication.dao;




import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nutritionapplication.model.User;




@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table where username= :mail and password= :password")
    User getUser(String mail, String password);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);



}
