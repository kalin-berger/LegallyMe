package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;

import java.net.URL;

@Entity(tableName = "record_change_form_table", primaryKeys = {"state", "type", "field", "title"})
public class RecordChangeForm {

    public RecordChangeForm(@NonNull RecordState state, @NonNull RecordType type, @NonNull RecordField field, @NonNull String title, @NonNull URL url) {
        this.state = state;
        this.type = type;
        this.field = field;
        this.title = title;
        this.url = url;
    }

    @NonNull
    private RecordState state;

    @NonNull
    private RecordType type;

    @NonNull
    private RecordField field;

    @NonNull
    private String title;

    @NonNull
    private URL url;

    public RecordState getState() {
        return this.state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }

    public RecordType getType() {
        return this.type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    public RecordField getField() {
        return this.field;
    }

    public void setField(RecordField field) {
        this.field = field;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String result = "";
        result += "State: " + state + "\n"
                + "Type: " + type + "\n"
                + "Field: " + field + "\n"
                + "Title: " + title + "\n"
                + "URL: " + url;

        return result;
    }
}
