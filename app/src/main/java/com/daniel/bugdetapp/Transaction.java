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
    private int quantity;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private Date timestamp;

    public Transaction(@NonNull int id, int quantity, Date timestamp) {
        this.id = id;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    @NonNull
    public Date getTimestamp() {
        return timestamp;
    }
}
