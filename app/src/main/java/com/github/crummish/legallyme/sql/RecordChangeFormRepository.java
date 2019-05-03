package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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

    public void deleteAll() {
        recordChangeFormDao.deleteAll();
    }
}
