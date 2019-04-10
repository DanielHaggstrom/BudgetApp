package com.daniel.bugdetapp;

import org.threeten.bp.*;
import org.threeten.bp.format.DateTimeFormatter;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

public class Converters {
    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @TypeConverter
    public static OffsetDateTime toOffsetDateTime(@NonNull String value){
        return OffsetDateTime.parse(value, formatter);
    }

    @TypeConverter
    public static String toString(@NonNull OffsetDateTime value){
        return value.format(formatter);
    }
}
