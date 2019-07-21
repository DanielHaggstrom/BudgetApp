package com.daniel.bugdetapp;

import androidx.room.TypeConverter;

import java.math.BigDecimal;

public class Converters {
    @TypeConverter
    public static float fromBigDecimal(BigDecimal decimal){
        return decimal.byteValue();
    }

    @TypeConverter
    public static BigDecimal fromFloat(float decimal){
        return new BigDecimal(decimal);
    }
}
