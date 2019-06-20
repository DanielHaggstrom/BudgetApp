package com.daniel.bugdetapp;

import android.app.Application;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TransactionViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<Transaction>> mAllTransactions;

    public TransactionViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllTransactions = mRepository.getAllTransactions();
    }

    LiveData<List<Transaction>> getAll() { return mAllTransactions; }

    public void insert(Transaction transaction) {mRepository.insertTransaction(transaction); }

    LiveData<List<Transaction>> getWeek(String weekStart, String weekEnd) { return mRepository.getTransactionsFromWeek(weekStart, weekEnd); }
}
