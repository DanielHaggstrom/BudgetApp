package com.daniel.bugdetapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WEEKS")
public class Week {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "key")
    private int key;

    @NonNull
    @ColumnInfo(name = "week_balance")
    private float balance;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public Week(@NonNull float balance) {
        this.balance = balance;
        this.timestamp = Logic.getCurrentWeek();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }
}