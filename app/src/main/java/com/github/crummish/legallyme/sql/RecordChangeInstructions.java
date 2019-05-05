package com.github.crummish.legallyme.sql;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

@Entity(tableName = "record_change_instructions_table", primaryKeys = {"state", "type", "field", "stepNo"})
public class RecordChangeInstructions {

    @NonNull
    private RecordState state;

    @NonNull
    private RecordType type;

    @NonNull
    private RecordField field;

    @NonNull
    private int stepNo;

    @NonNull
    private String instructions;

    public RecordChangeInstructions(@NonNull RecordState state, @NonNull RecordType type, @NonNull RecordField field, int stepNo, @NonNull String instructions) {
        this.state = state;
        this.type = type;
        this.field = field;
        this.stepNo = stepNo;
        this.instructions = instructions;
    }

    @NonNull
    public RecordState getState() {
        return state;
    }

    public void setState(@NonNull RecordState state) {
        this.state = state;
    }

    @NonNull
    public RecordType getType() {
        return type;
    }

    public void setType(@NonNull RecordType type) {
        this.type = type;
    }

    @NonNull
    public RecordField getField() {
        return field;
    }

    public void setField(@NonNull RecordField field) {
        this.field = field;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    @NonNull
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(@NonNull String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        String result = "";
        result += "State: " + state + "\n"
                + "Type: " + type + "\n"
                + "Field: " + field + "\n"
                + "Step: " + stepNo + "\n"
                + "Instructions: " + instructions;

        return result;
    }
}
