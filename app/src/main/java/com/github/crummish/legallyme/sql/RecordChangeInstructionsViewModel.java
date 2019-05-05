package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.List;

public class RecordChangeInstructionsViewModel extends AndroidViewModel {
    private RecordChangeInstructionsRepository repository;
    private LiveData<List<RecordChangeInstructions>> allInstructions;

    public RecordChangeInstructionsViewModel(Application application) {
        super(application);
        repository = new RecordChangeInstructionsRepository(application);
        allInstructions = repository.getAllInstructions();
    }

    public LiveData<List<RecordChangeInstructions>> getAllInstructions() {
        return allInstructions;
    }

    public void insert(RecordChangeInstructions instructions) {
        repository.insert(instructions);
    }

    public List<RecordChangeInstructions> findInstructions(RecordState state, RecordType type, RecordField field) {
        return repository.findInstructions(state, type, field);
    }
}
