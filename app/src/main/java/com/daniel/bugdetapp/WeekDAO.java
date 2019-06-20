package com.daniel.bugdetapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

public interface WeekDAO extends Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Week week);

    @Query("SELECT * from WEEKS ORDER BY `key` DESC")
    LiveData<List<Week>> getAll();

    @Query("SELECT * from WEEKS ORDER BY `key` DESC limit 5")
    LiveData<List<Week>> getLasts();

    @Query("DELETE FROM WEEKS")
    void deleteAll();
}
