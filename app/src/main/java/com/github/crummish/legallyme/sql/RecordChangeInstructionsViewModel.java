package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

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

}
