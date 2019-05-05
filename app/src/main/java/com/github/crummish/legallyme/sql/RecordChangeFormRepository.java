package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.ArrayList;
import java.util.List;

public class RecordChangeFormRepository {
    private RecordChangeFormDao recordChangeFormDao;
    private LiveData<List<RecordChangeForm>> allForms;

    public RecordChangeFormRepository(Application application) {
        RecordChangeDatabase db = RecordChangeDatabase.getDatabase(application);
        recordChangeFormDao = db.recordChangeFormDao();
        allForms = recordChangeFormDao.getAllForms();
    }

    LiveData<List<RecordChangeForm>> getAllForms() {
        return allForms;
    }

    public void insert(RecordChangeForm form) {
        (new InsertAsyncTask(recordChangeFormDao)).execute(form);
    }

    public List<RecordChangeForm> findForms(RecordState state, RecordType type, RecordField field) {
        return recordChangeFormDao.findForms(state, type, field);
    }

    private static class InsertAsyncTask extends AsyncTask<RecordChangeForm, Void, Void> {
        private RecordChangeFormDao recordChangeFormDao;

        InsertAsyncTask(RecordChangeFormDao recordChangeFormDao) {
            this.recordChangeFormDao = recordChangeFormDao;
        }

        @Override
        protected Void doInBackground(final RecordChangeForm... params) {
            recordChangeFormDao.insert(params[0]);
            return null;
        }
    }
}
