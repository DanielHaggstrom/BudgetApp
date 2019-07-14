package com.daniel.bugdetapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.daniel.bugdetapp";

    final private String BASE_AMOUNT = "base_amount";
    final private String CURRENT_WEEK = "current_week";
    final private String TARGET = "target";

    private float base_amount;
    private String currentWeek;
    private float target;

    private float balance;

    private FundsViewModel mFundsViewModel;

    private TransactionViewModel mTransactionViewModel;

    public static final int NEW_TRANSACTION_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSharedPreferences();
        this.mFundsViewModel = ViewModelProviders.of(this).get(FundsViewModel.class);
        mFundsViewModel.getWeekTransactions().observe(this, new Observer<List<Transaction>>() {

            @Override
            public void onChanged(List<Transaction> transactions) {
                balance = Logic.getWeekBalance(transactions);
                setTextAndProgress();
            }
        });
        mTransactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!Logic.isWeekCorrect(currentWeek)){
            target = target + balance + base_amount;
            currentWeek = Logic.getCurrentWeek();
        }
        setTextAndProgress();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, HistoricalData.class);
        startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(CURRENT_WEEK, currentWeek);
        preferencesEditor.putFloat(TARGET, target);
        /*TODO
        * Add way of changing base amount
        * */
        preferencesEditor.apply();
    }

    private void loadSharedPreferences(){
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        base_amount = mPreferences.getFloat(BASE_AMOUNT, 40);
        currentWeek = mPreferences.getString(CURRENT_WEEK, Logic.getCurrentWeek());
        target = mPreferences.getFloat(TARGET, base_amount);
}

    private void setTextAndProgress() {
        TextView funds = findViewById(R.id.show_funds);
        ProgressBar bar = findViewById(R.id.progressBar);
        funds.setText(Float.toString(target + balance) + " €");
        if (target + balance > target * 0.5) {
            funds.setTextColor(Color.GREEN);
            bar.setVisibility(View.VISIBLE);
        }
        else if (target + balance >= 0) {
            funds.setTextColor(Color.YELLOW);
            bar.setVisibility(View.VISIBLE);
        }
        else if (target + balance < 0) {
            funds.setTextColor(Color.RED);
            bar.setVisibility(View.INVISIBLE);
        }
        bar.setProgress(Math.round(100* (target + balance)/target));
    }

    public void launchNewTransaction(View view){
        Intent intent = new Intent(this, NewTransactionActivity.class);
        startActivityForResult(intent, NEW_TRANSACTION_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRANSACTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Transaction word = new Transaction(-1 * Float.parseFloat(data.getStringExtra(NewTransactionActivity.EXTRA_REPLY)));
            mTransactionViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.error_input_data,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

}
