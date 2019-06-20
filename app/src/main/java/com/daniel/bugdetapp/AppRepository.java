package com.daniel.bugdetapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;

public class AppRepository {

    private TransactionDAO mTransactionDao;
    private LiveData<List<Transaction>> mAllTransactions;

    private WeekDAO mWeekDao;
    private LiveData<List<Week>> mAllWeeks;
    private LiveData<List<Week>> mLastsWeeks;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTransactionDao = db.transactionDAO();
        mAllTransactions = mTransactionDao.getAll();
        mWeekDao = db.weekDAO();
        mAllWeeks = mWeekDao.getAll();
        mLastsWeeks = mWeekDao.getLasts();
    }

    LiveData<List<Transaction>> getAllTransactions() {
        return mAllTransactions;
    }

    LiveData<List<Week>> getAllWeeks() { return mAllWeeks; }

    LiveData<List<Week>> getLastsWeeks() { return mLastsWeeks; }


    LiveData<List<Transaction>> getTransactionsFromWeek(String weekStart, String weekEnd) {
        try {
            return new getWeekAsyncTask(mTransactionDao).execute(weekStart, weekEnd).get();
        }
        catch (Exception e){
            return null;
        }
    }

    public void insertTransaction(Transaction transaction) {
        new insertTransactionAsyncTask(mTransactionDao).execute(transaction);
    }

    public void insertWeek(Week week) {
        new insertWeekAsyncTask(mWeekDao).execute(week);
    }

    private static class insertTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDAO mAsyncTaskDao;

        insertTransactionAsyncTask(TransactionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertWeekAsyncTask extends AsyncTask<Week, Void, Void> {

        private WeekDAO mAsyncTaskDao;

        insertWeekAsyncTask(WeekDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Week... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class getWeekAsyncTask extends AsyncTask<String, Void, LiveData<List<Transaction>>> {

        private TransactionDAO mAsyncTaskDao;

        getWeekAsyncTask(TransactionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected LiveData<List<Transaction>> doInBackground(final String... params) {
            return mAsyncTaskDao.getWeek(params[0], params[1]);
        }
    }
}
