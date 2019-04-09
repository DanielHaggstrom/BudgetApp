package com.daniel.bugdetapp;

import android.app.Application;
import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository mRepository;

    private LiveData<List<Transaction>> mAllTransactions;

    public TransactionViewModel (Application application) {
        super(application);
        mRepository = new TransactionRepository(application);
        mAllTransactions = mRepository.getAll();
    }

    LiveData<List<Transaction>> getAll() { return mAllTransactions; }

    public void insert(Transaction transaction) {mRepository.insert(transaction); }
}
