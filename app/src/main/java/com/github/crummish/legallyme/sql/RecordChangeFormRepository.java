package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.List;

public class RecordChangeFormRepository {
    private RecordChangeFormDao recordChangeFormDao;
    private LiveData<List<RecordChangeForm>> allForms;
    private MutableLiveData<List<RecordChangeForm>> findFormsResults = new MutableLiveData<>();

    public RecordChangeFormRepository(Application application) {
        RecordChangeDatabase db = RecordChangeDatabase.getDatabase(application);
        recordChangeFormDao = db.recordChangeFormDao();
        allForms = recordChangeFormDao.getAllForms();
    }

    LiveData<List<RecordChangeForm>> getAllForms() {
        return allForms;
    }

    MutableLiveData<List<RecordChangeForm>> getFindFormsResults() {
        return findFormsResults;
    }

    public void insert(RecordChangeForm form) {
        (new InsertAsyncTask(recordChangeFormDao)).execute(form);
    }

    public void findForms(RecordState state, RecordType type, RecordField field) {
        (new FindFormsAsyncTask(recordChangeFormDao, this, state, type, field))
                .execute();
    }

    public void findFormsFinished(List<RecordChangeForm> result) {
        findFormsResults.setValue(result);
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

    private static class FindFormsAsyncTask extends AsyncTask<Void, Void, List<RecordChangeForm>> {
        private RecordChangeFormDao recordChangeFormDao;
        private RecordChangeFormRepository delegate;

        private RecordState state;
        private RecordType type;
        private RecordField field;

        FindFormsAsyncTask(RecordChangeFormDao recordChangeFormDao,
                           RecordChangeFormRepository delegate,
                           RecordState state,
                           RecordType type,
                           RecordField field) {
            this.recordChangeFormDao = recordChangeFormDao;
            this.delegate = delegate;
            this.state = state;
            this.type = type;
            this.field = field;
        }

        @Override
        protected List<RecordChangeForm> doInBackground(final Void... params) {
            return recordChangeFormDao.findForms(state, type, field);
        }

        @Override
        protected void onPostExecute(List<RecordChangeForm> result) {
            delegate.findFormsFinished(result);
        }
    }
}
