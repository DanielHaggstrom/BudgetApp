package com.daniel.bugdetapp;

import java.math.BigDecimal;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TransactionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Transaction transaction);

    @Query("SELECT * from TRANSACTIONS ORDER BY `key` DESC")
    LiveData<List<Transaction>> getAll();

    @Query("SELECT * from TRANSACTIONS WHERE timestamp BETWEEN :weekStart AND :weekEnd ORDER BY `key` DESC")
    LiveData<List<Transaction>> getWeek(String weekStart, String weekEnd);

    @Query("DELETE FROM TRANSACTIONS")
    void deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Transaction transaction);

}
