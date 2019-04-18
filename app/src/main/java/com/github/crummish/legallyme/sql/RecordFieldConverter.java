package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.TypeConverter;

import com.github.crummish.legallyme.document.RecordField;

public class RecordFieldConverter {

    @TypeConverter
    public static RecordField fromString(String value) {
        String formattedValue = value.toUpperCase().replaceAll(" ", "_");
        return RecordField.valueOf(formattedValue);
    }

    @TypeConverter
    public static String toString(RecordField field) {
        return field.toString();
    }


}
