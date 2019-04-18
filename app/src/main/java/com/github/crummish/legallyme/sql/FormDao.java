package com.github.crummish.legallyme.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FormDao {

    @Insert
    void insert(RecordChangeForm form);

    @Query("DELETE FROM record_change_form_table")
    void deleteAll();

    @Query("SELECT * FROM record_change_form_table ORDER BY state")
    LiveData<List<RecordChangeForm>> getAllForms();
}
