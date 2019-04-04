package com.daniel.bugdetapp;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import androidx.lifecycle.LiveData;

public class TransactionRepository {

    private TransactionDAO mTransactionDao;
    private LiveData<List<Transaction>> mAllTransactions;

    TransactionRepository(Application application) {
        TransactionRoomDatabase db = TransactionRoomDatabase.getDatabase(application);
        mTransactionDao = db.transactionDAO();
        mAllTransactions = mTransactionDao.getAll();
    }

    LiveData<List<Transaction>> getAll() {
        return mAllTransactions;
    }

    public void insert (Transaction transaction) {
        Log.e("DEBUG","<----------------- Insert called");
        new insertAsyncTask(mTransactionDao).execute(transaction);
    }

    private static class insertAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDAO mAsyncTaskDao;

        insertAsyncTask(TransactionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
