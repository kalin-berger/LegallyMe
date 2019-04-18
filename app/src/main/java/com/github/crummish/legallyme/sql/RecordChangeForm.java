package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;

import java.net.URL;

@Entity(tableName = "record_change_form_table")
public class RecordChangeForm {

    @TypeConverters(RecordStateConverter.class)
    @PrimaryKey
    @NonNull
    private RecordState state;

    @TypeConverters(RecordTypeConverter.class)
    @PrimaryKey
    @NonNull
    private RecordType type;

    @TypeConverters(RecordFieldConverter.class)
    @PrimaryKey
    @NonNull
    private RecordField field;

    private String title;

    @TypeConverters(UrlConverter.class)
    private URL url;
}
