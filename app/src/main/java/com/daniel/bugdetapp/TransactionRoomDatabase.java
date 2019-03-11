package com.daniel.bugdetapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
public abstract class TransactionRoomDatabase extends RoomDatabase {

}