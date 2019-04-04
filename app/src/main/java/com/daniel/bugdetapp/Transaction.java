package com.daniel.bugdetapp;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TRANSACTIONS")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "key")
    private int id;

    @NonNull
    @ColumnInfo(name = "quantity")
    private float quantity;

    public Transaction(@NonNull int id, float quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public float getQuantity() {
        return quantity;
    }
}
