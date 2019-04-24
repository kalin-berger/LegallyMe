package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FormRepository {
    private FormDao formDao;
    private LiveData<List<RecordChangeForm>> allForms;

    public FormRepository(Application application) {
        FormRoomDatabase db = FormRoomDatabase.getDatabase(application);
        formDao = db.formDao();
        allForms = formDao.getAllForms();
    }

    LiveData<List<RecordChangeForm>> getAllForms() {
        return allForms;
    }

    public void insert(RecordChangeForm form) {
        (new InsertAsyncTask(formDao)).execute(form);
    }

    private static class InsertAsyncTask extends AsyncTask<RecordChangeForm, Void, Void> {
        private FormDao formDao;

        InsertAsyncTask(FormDao formDao) {
            this.formDao = formDao;
        }

        @Override
        protected Void doInBackground(final RecordChangeForm... params) {
            formDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll() {
        formDao.deleteAll();
    }
}
