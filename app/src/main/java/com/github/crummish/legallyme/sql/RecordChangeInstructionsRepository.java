package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RecordChangeInstructionsRepository {
    private RecordChangeInstructionsDao recordChangeInstructionsDao;
    private LiveData<List<RecordChangeInstructions>> allInstructions;

    public RecordChangeInstructionsRepository(Application application) {
        RecordChangeDatabase db = RecordChangeDatabase.getDatabase(application);
        recordChangeInstructionsDao = db.recordChangeInstructionsDao();
        allInstructions = recordChangeInstructionsDao.getAllInstructions();
    }

    LiveData<List<RecordChangeInstructions>> getAllInstructions() {
        return allInstructions;
    }

    public void insert(RecordChangeInstructions instructions) {
        (new InsertAsyncTask(recordChangeInstructionsDao)).execute(instructions);
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

    public void deleteAll() {
        recordChangeInstructionsDao.deleteAll();
    }
}
