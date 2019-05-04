package com.github.crummish.legallyme.sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecordChangeInstructionsDao {

    @Insert
    void insert(RecordChangeInstructions form);

    @Query("DELETE FROM record_change_instructions_table")
    void deleteAll();

    @Query("SELECT * FROM record_change_instructions_table ORDER BY state")
    LiveData<List<RecordChangeInstructions>> getAllInstructions();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE type = 'RecordType.GENERAL' AND field = 'RecordField.NAME'")
    LiveData<List<RecordChangeInstructions>> getGeneral();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE type = 'RecordType.BIRTH_CERTIFICATE' AND field = 'RecordField.GENDER_MARKER'")
    LiveData<List<RecordChangeInstructions>> getBirthCertificateGender();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE type = 'RecordType.BIRTH_CERTIFICATE' AND field = 'RecordField.NAME'")
    LiveData<List<RecordChangeInstructions>> getBirthCertificateName();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE type = 'RecordType.DRIVERS_LICENSE' AND field = 'RecordField.GENDER_MARKER'")
    LiveData<List<RecordChangeInstructions>> getDriversLicenseGender();

    @Query("SELECT instructions, stepNo FROM record_change_instructions_table WHERE type = 'RecordType.DRIVERS_LICENSE' AND field = 'RecordField.NAME'")
    LiveData<List<RecordChangeInstructions>> getDriversLicenseName();
}
