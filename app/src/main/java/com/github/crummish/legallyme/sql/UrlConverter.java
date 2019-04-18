package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.TypeConverter;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlConverter {

    @TypeConverter
    public URL fromString(String value) {
        URL result;
        try {
            result = new URL(value);
        } catch(MalformedURLException e) {
            result = null;
        }

        return result;
    }

    @TypeConverter
    public String toString(URL url) {
        return url.toString();
    }
}
