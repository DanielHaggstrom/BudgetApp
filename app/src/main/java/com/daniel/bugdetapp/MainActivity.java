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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.daniel.bugdetapp";

    final private String BASE_AMOUNT = "base_amount";
    final private String CURRENT_WEEK = "current_week";
    final private String TARGET = "target";

    private String base_amount_save;
    private BigDecimal base_amount;
    private String currentWeek;
    private String target_save;
    private BigDecimal target;

    private BigDecimal balance;

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
            target = target.add(balance.add(base_amount));
            currentWeek = Logic.getCurrentWeek();
        }
        //setTextAndProgress();
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
        preferencesEditor.putString(TARGET, target.
                setScale(2, BigDecimal.ROUND_HALF_EVEN).toPlainString());
        /*TODO
        * Add way of changing base amount
        * */
        preferencesEditor.apply();
    }

    private void loadSharedPreferences(){
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Preferences.edit().clear().commit();
        base_amount_save = mPreferences.getString(BASE_AMOUNT, "40.00");
        base_amount = new BigDecimal(base_amount_save).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        currentWeek = mPreferences.getString(CURRENT_WEEK, Logic.getCurrentWeek());
        target_save = mPreferences.getString(TARGET,base_amount_save);
        target = new BigDecimal(target_save).setScale(2, BigDecimal.ROUND_HALF_EVEN);
}

    private void setTextAndProgress() {
        TextView funds = findViewById(R.id.show_funds);
        ProgressBar bar = findViewById(R.id.progressBar);
        BigDecimal current = target.add(balance).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        funds.setText(current.toString() + " €");
        if (current.compareTo(target.multiply(new BigDecimal("0.5"))
                .setScale(2, BigDecimal.ROUND_HALF_EVEN)) > 0) {
            funds.setTextColor(Color.GREEN);
            bar.setVisibility(View.VISIBLE);
        }
        else if (current.compareTo(BigDecimal.ZERO) >= 0) {
            funds.setTextColor(Color.YELLOW);
            bar.setVisibility(View.VISIBLE);
        }
        else if (current.compareTo(BigDecimal.ZERO) < 0) {
            funds.setTextColor(Color.RED);
            bar.setVisibility(View.INVISIBLE);
        }
        bar.setProgress(Math.round(100*Float.parseFloat(
                current.divide(target, BigDecimal.ROUND_HALF_EVEN).toPlainString())));
    }

    public void launchNewTransaction(View view){
        Intent intent = new Intent(this, NewTransactionActivity.class);
        startActivityForResult(intent, NEW_TRANSACTION_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRANSACTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            BigDecimal minusOne = BigDecimal.valueOf(-1)
                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal convertedData = new BigDecimal(
                    data.getStringExtra(NewTransactionActivity.EXTRA_REPLY))
                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
            Transaction newTransaction = new Transaction(minusOne.multiply(convertedData));
            mTransactionViewModel.insert(newTransaction);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.error_input_data,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

}
