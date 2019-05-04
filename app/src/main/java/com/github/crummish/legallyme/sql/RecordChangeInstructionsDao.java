package com.github.crummish.legallyme.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.List;

@Dao
public interface RecordChangeInstructionsDao {

    @Insert
    void insert(RecordChangeInstructions form);

    @Query("DELETE FROM record_change_instructions_table")
    void deleteAll();

    @Query("SELECT * FROM record_change_instructions_table ORDER BY state")
    LiveData<List<RecordChangeInstructions>> getAllInstructions();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE " +
            "state LIKE :state AND " +
            "type LIKE :type AND " +
            "field LIKE :field")
    LiveData<List<RecordChangeInstructions>> findInstructions(RecordState state, RecordType type, RecordField field);
}
