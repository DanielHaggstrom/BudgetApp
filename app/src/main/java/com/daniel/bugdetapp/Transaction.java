package com.daniel.bugdetapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Transaction {
    @PrimaryKey
    public Date id;

    @ColumnInfo(name = "amount")//positive adds money, negative removes it
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
