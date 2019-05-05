package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.List;

public class RecordChangeFormViewModel extends AndroidViewModel {
    private RecordChangeFormRepository repository;
    private LiveData<List<RecordChangeForm>> allForms;
    private MutableLiveData<List<RecordChangeForm>> findFormsResults;

    public RecordChangeFormViewModel(Application application) {
        super(application);
        repository = new RecordChangeFormRepository(application);
        allForms = repository.getAllForms();
        findFormsResults = repository.getFindFormsResults();
    }

    public LiveData<List<RecordChangeForm>> getAllForms() {
        return allForms;
    }

    public MutableLiveData<List<RecordChangeForm>> getFindFormsResults() {
        return findFormsResults;
    }

    public void insert(RecordChangeForm form) {
        repository.insert(form);
    }

    public void findForms(RecordState state, RecordType type, RecordField field) {
        repository.findForms(state, type, field);
    }
}
