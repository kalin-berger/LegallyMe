package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.List;

public class RecordChangeInstructionsRepository {
    private RecordChangeInstructionsDao recordChangeInstructionsDao;
    private LiveData<List<RecordChangeInstructions>> allInstructions;
    private MutableLiveData<List<RecordChangeInstructions>> findInstructionsResults = new MutableLiveData<>();

    public RecordChangeInstructionsRepository(Application application) {
        RecordChangeDatabase db = RecordChangeDatabase.getDatabase(application);
        recordChangeInstructionsDao = db.recordChangeInstructionsDao();
        allInstructions = recordChangeInstructionsDao.getAllInstructions();
    }

    public LiveData<List<RecordChangeInstructions>> getAllInstructions() {
        return allInstructions;
    }

    public void insert(RecordChangeInstructions instructions) {
        (new InsertAsyncTask(recordChangeInstructionsDao)).execute(instructions);
    }

    public MutableLiveData<List<RecordChangeInstructions>> getFindInstructionsResults() {
        return findInstructionsResults;
    }

    public void findInstructions(RecordState state, RecordType type, RecordField field) {
        (new FindInstructionsAsyncTask(recordChangeInstructionsDao, this, state, type, field))
                .execute();
    }

    public void findInstructionsFinished(List<RecordChangeInstructions> result) {
        if(result == null) {
            Log.e("Repository", "Result was null!");
        }
        findInstructionsResults.setValue(result);
    }

    private static class InsertAsyncTask extends AsyncTask<RecordChangeInstructions, Void, Void> {
        private RecordChangeInstructionsDao recordChangeInstructionsDao;

        InsertAsyncTask(RecordChangeInstructionsDao recordChangeInstructionsDao) {
            this.recordChangeInstructionsDao = recordChangeInstructionsDao;
        }

        @Override
        protected Void doInBackground(final RecordChangeInstructions... params) {
            recordChangeInstructionsDao.insert(params[0]);
            return null;
        }
    }

    private static class FindInstructionsAsyncTask extends AsyncTask<Void, Void, List<RecordChangeInstructions>> {
        private RecordChangeInstructionsDao recordChangeInstructionsDao;
        private RecordChangeInstructionsRepository delegate;

        private RecordState state;
        private RecordType type;
        private RecordField field;

        FindInstructionsAsyncTask(RecordChangeInstructionsDao recordChangeInstructionsDao,
                                  RecordChangeInstructionsRepository delegate,
                                  RecordState state,
                                  RecordType type,
                                  RecordField field) {
            this.recordChangeInstructionsDao = recordChangeInstructionsDao;
            this.delegate = delegate;
            this.state = state;
            this.type = type;
            this.field = field;
        }

        @Override
        protected List<RecordChangeInstructions> doInBackground(final Void... params) {
            return recordChangeInstructionsDao.findInstructions(state, type, field);
        }

        @Override
        protected void onPostExecute(List<RecordChangeInstructions> result) {
            delegate.findInstructionsFinished(result);
        }
    }
}
