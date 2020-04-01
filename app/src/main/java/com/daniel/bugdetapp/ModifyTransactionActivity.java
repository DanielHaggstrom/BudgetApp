package com.daniel.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ModifyTransactionActivity extends AppCompatActivity {

    private EditText mEditTransactionView;
    public static final String EXTRA_REPLY =
            "com.example.android.bugdetapp.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_transaction);
        Intent intent = getIntent();
        mEditTransactionView = findViewById(R.id.editText);
        mEditTransactionView.setHint();
    }
}
