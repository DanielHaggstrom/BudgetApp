package com.daniel.bugdetapp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TransactionDAO {

    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * from TRANSACTIONS ORDER BY timestamp DESC")
    LiveData<List<Transaction>> getAll();
}
