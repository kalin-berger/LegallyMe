package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;

import java.net.URL;

@Entity(tableName = "record_change_form_table")
public class RecordChangeForm {

    @PrimaryKey(autoGenerate = true)
    private String id;

    @TypeConverters(RecordStateConverter.class)
    private RecordState state;

    @TypeConverters(RecordTypeConverter.class)
    private RecordType type;

    @TypeConverters(RecordFieldConverter.class)
    private RecordField field;

    private String title;

    @TypeConverters(UrlConverter.class)
    private URL url;
}
