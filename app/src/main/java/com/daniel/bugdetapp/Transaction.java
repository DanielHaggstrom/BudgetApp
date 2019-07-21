package com.daniel.bugdetapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.threeten.bp.*;

@Entity(tableName = "TRANSACTIONS")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "key")
    private int key;

    @NonNull
    @ColumnInfo(name = "quantity")
    private float quantity;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public Transaction(@NonNull float quantity) {
        this.quantity = quantity;
        this.timestamp = LocalDate.now().toString();
    }

    public int getKey() {
        return key;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setKey(int key){
        this.key = key;
    }

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }
}