package com.daniel.bugdetapp;

import android.app.Application;
import android.os.AsyncTask;

import java.math.BigDecimal;
import java.util.List;
import androidx.lifecycle.LiveData;

public class AppRepository {

    private TransactionDAO mTransactionDao;
    private LiveData<List<Transaction>> mAllTransactions;
    private LiveData<List<Transaction>> mWeekTransactions;

    private WeekDAO mWeekDao;
    private LiveData<List<Week>> mAllWeeks;
    private LiveData<List<Week>> mLastsWeeks;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTransactionDao = db.transactionDAO();
        mAllTransactions = mTransactionDao.getAll();
        mWeekTransactions = mTransactionDao.getWeek(Logic.getCurrentWeek(), Logic.getNextWeek());
        mWeekDao = db.weekDAO();
        mAllWeeks = mWeekDao.getAll();
        mLastsWeeks = mWeekDao.getLasts();
    }

    LiveData<List<Transaction>> getAllTransactions() {
        return mAllTransactions;
    }

    LiveData<List<Transaction>> getTransactionsFromWeek() {
        return mWeekTransactions;
    }

    LiveData<List<Week>> getAllWeeks() { return mAllWeeks; }

    LiveData<List<Week>> getLastsWeeks() { return mLastsWeeks; }


    public void insertTransaction(Transaction transaction) {
        new insertTransactionAsyncTask(mTransactionDao).execute(transaction);
    }

    public void insertWeek(Week week) {
        new insertWeekAsyncTask(mWeekDao).execute(week);
    }

    public void modify(Transaction transaction) {
        new modifyTransactionAsyncTask(mTransactionDao).execute(transaction);
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

    private static class modifyTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDAO mAsyncTaskDao;

        modifyTransactionAsyncTask(TransactionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Transaction... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
