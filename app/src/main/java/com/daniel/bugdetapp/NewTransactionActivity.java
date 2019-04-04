package com.daniel.bugdetapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class NewTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";

    private EditText mEditTransactionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
    }

    public void saveToDatabase(View view) {
        Intent replyIntent = new Intent();
        if (mEditTransactionView.getText() == null) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = mEditTransactionView.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

}
