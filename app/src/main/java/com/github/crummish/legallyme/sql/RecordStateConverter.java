package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.TypeConverter;

import com.github.crummish.legallyme.document.RecordState;

public class RecordStateConverter {

    @TypeConverter
    public static RecordState fromString(String value) {
        String formattedValue = value.toUpperCase().replaceAll(" ", "_");
        return RecordState.valueOf(formattedValue);
    }

    @TypeConverter
    public static String toString(RecordState field) {
        return field.toString();
    }


}
