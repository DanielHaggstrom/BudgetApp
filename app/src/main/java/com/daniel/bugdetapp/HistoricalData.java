package com.daniel.bugdetapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoricalData extends AppCompatActivity {

    private TransactionViewModel mTransactionViewModel;
    public static final int MODIFY_TRANSACTION_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void show(List<Transaction> transactionList) {
        for (int i = 0; i < transactionList.size(); i++) {
            Log.d("update", transactionList.get(i).getQuantity().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_historical_data);
        final TransactionListAdapter adapter = new TransactionListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTransactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        mTransactionViewModel.getAll().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactions) {
                adapter.setTransactions(transactions);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MODIFY_TRANSACTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra("ID", 0);
            BigDecimal amount = new BigDecimal(data.getStringExtra("com.example.android.bugdetapp.REPLY-DOUBLE"));
            BigDecimal minusOne = BigDecimal.valueOf(-1).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            Transaction newTransaction = new Transaction(minusOne.multiply(amount));
            newTransaction.setKey(id);
            mTransactionViewModel.modify(newTransaction);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.error_input_data,
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
