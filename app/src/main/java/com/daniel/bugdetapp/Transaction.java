package com.daniel.bugdetapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TRANSACTIONS")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "key")
    private int key;

    @NonNull
    @ColumnInfo(name = "quantity")
    private float quantity;

    public Transaction(@NonNull float quantity) {
        this.quantity = quantity;
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
}