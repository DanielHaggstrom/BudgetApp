package com.daniel.bugdetapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.daniel.bugdetapp";
    final private String BASE_AMOUNT = "base_amount";
    final private String CURRENT_WEEK = "current_week";
    final private String SAVED = "saved";
    private float base_amount;
    private String currentWeek;
    private float saved;
    private float target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        base_amount = mPreferences.getFloat(BASE_AMOUNT, 40);
        currentWeek = mPreferences.getString(CURRENT_WEEK, Logic.getCurrentWeek());
        saved = mPreferences.getFloat(SAVED, 0);
        target = saved + base_amount;

        if (!Logic.isWeekCorrect(currentWeek)){
            saved = Logic.getWeekBalance(this.getApplication(), currentWeek, target);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, HistoricalData.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(CURRENT_WEEK, Logic.getCurrentWeek());
        preferencesEditor.putFloat(SAVED, saved);
        preferencesEditor.apply();
    }

}
