package com.daniel.bugdetapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FundsViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<Transaction>> mWeekTransactions;

    public FundsViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mWeekTransactions = mRepository.getTransactionsFromWeek();
    }

    LiveData<List<Transaction>> getWeekTransactions () {
        return mWeekTransactions;
    }
}
