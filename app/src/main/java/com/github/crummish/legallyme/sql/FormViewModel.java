package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class FormViewModel extends AndroidViewModel {
    private FormRepository repository;
    private LiveData<List<RecordChangeForm>> allForms;

    public FormViewModel(Application application) {
        super(application);
        repository = new FormRepository(application);
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
