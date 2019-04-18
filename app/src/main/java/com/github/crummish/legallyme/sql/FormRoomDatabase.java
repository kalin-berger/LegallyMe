package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RecordChangeForm.class}, version = 1)
public abstract class FormRoomDatabase extends RoomDatabase {
    public abstract FormDao formDao();

    private static volatile FormRoomDatabase INSTANCE;

    static FormRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (FormRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FormRoomDatabase.class, "form_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
