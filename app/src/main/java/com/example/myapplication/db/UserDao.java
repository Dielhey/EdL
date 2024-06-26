package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    User getById(long id);

    // Regarde si un utilisateur n'existe pas déjà
    @Query("SELECT * FROM users WHERE nom = :nom AND prenom = :prenom LIMIT 1")
    User getDuplicate(String nom, String prenom);

    @Insert
    long insert(User user);

    @Insert
    long[] insertAll(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
