package com.daniel.bugdetapp;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Transaction transaction);

    @Query("SELECT * from TRANSACTIONS ORDER BY `key` DESC")
    LiveData<List<Transaction>> getAll();

    @Query("SELECT * from TRANSACTIONS WHERE timestamp BETWEEN :weekStart AND :weekEnd ORDER BY `key` DESC")
    List<Transaction> getWeek(String weekStart, String weekEnd);

    @Query("DELETE FROM TRANSACTIONS")
    void deleteAll();
}
