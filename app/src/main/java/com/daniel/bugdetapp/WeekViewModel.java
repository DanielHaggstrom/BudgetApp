package com.daniel.bugdetapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WeekViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<Week>> mAllWeeks;
    private LiveData<List<Week>> mLastsWeeks;

    public WeekViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllWeeks = mRepository.getAllWeeks();
        mLastsWeeks = mRepository.getLastsWeeks();
    }

    LiveData<List<Week>> getAll() { return mAllWeeks; }

    LiveData<List<Week>> getLasts() { return mLastsWeeks; }

    public void insert(Week week) {mRepository.insertWeek(week); }
}
