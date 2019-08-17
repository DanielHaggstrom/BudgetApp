package com.daniel.bugdetapp;

import androidx.room.TypeConverter;

import java.math.BigDecimal;

public class Converters {
    @TypeConverter
    public static BigDecimal fromString(String value) {
        return value == null ? null : new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @TypeConverter
    public static String dateToTimestamp(BigDecimal value) {
        return value == null ? null : value.toPlainString();
    }
}
