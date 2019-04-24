package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.TypeConverter;

import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

public class RecordTypeConverter {

    @TypeConverter
    public static RecordType fromString(String value) {
        String formattedValue = value.toUpperCase().replaceAll(" ", "_");
        return RecordType.valueOf(formattedValue);
    }

    @TypeConverter
    public static String toString(RecordType field) {
        return field.toString();
    }


}
