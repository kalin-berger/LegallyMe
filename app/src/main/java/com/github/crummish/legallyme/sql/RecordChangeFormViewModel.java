package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class RecordChangeFormViewModel extends AndroidViewModel {
    private RecordChangeFormRepository repository;
    private LiveData<List<RecordChangeForm>> allForms;

    public RecordChangeFormViewModel(Application application) {
        super(application);
        repository = new RecordChangeFormRepository(application);
        allForms = repository.getAllForms();
    }

    public LiveData<List<RecordChangeForm>> getAllForms() {
        return allForms;
    }

    public void insert(RecordChangeForm form) {
        repository.insert(form);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
