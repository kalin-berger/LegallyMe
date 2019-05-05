package com.github.crummish.legallyme.sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.util.ArrayList;
import java.util.List;

public class RecordChangeInstructionsRepository {
    private RecordChangeInstructionsDao recordChangeInstructionsDao;
    private LiveData<List<RecordChangeInstructions>> allInstructions;

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

    public List<RecordChangeInstructions> findInstructions(RecordState state, RecordType type, RecordField field) {
        return recordChangeInstructionsDao.findInstructions(state, type, field);
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
}
