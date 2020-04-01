package com.daniel.bugdetapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.threeten.bp.*;

import java.math.BigDecimal;

@Entity(tableName = "TRANSACTIONS")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "key")
    private int key;

    @NonNull
    @ColumnInfo(name = "quantity")
    private BigDecimal quantity;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private String timestamp;

    public Transaction(@NonNull BigDecimal quantity) {
        this.quantity = quantity;
        this.timestamp = LocalDate.now().toString();
    }

    public int getKey() {
        return key;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(@NonNull BigDecimal quantity) {
        this.quantity = quantity;
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