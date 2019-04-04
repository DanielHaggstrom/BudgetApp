package com.daniel.bugdetapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;


public class HistoricalData extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private TransactionViewModel mTransactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_data);
    }

    public void launchNewTransaction(View view){
        Intent intent = new Intent(this, NewTransactionActivity.class);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Transaction word = new Transaction(0, Integer.parseInt(data.getStringExtra(NewTransactionActivity.EXTRA_REPLY)));
            mTransactionViewModel.insert(word);
        }
    }
}
