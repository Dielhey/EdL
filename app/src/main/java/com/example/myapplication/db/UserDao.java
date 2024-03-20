package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface UserDao {

    @Query("select * FROM users")
    List<User> getAll();

    @Insert
    long insert(User user);

    @Insert
    long[] insertAll(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
