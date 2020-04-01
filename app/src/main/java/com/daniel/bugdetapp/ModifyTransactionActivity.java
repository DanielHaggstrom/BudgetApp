package com.daniel.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class ModifyTransactionActivity extends AppCompatActivity {

    private EditText mEditTransactionView;
    public static final String EXTRA_REPLY =
            "com.example.android.bugdetapp.REPLY-MODIFY";
    public static final String DOUBLE_REPLY =
            "com.example.android.bugdetapp.REPLY-DOUBLE";
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_transaction);
        Intent intent = getIntent();
        mEditTransactionView = findViewById(R.id.editText);
        mEditTransactionView.setHint(intent.getStringExtra("Modify"));
        this.id = intent.getIntExtra("ID", 0);
    }

    public void returnReply(View view) {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(mEditTransactionView.getText())){
            setResult(RESULT_CANCELED, replyIntent);
        }
        else if ((mEditTransactionView.getText().toString()).equals(".")){
            setResult(RESULT_CANCELED, replyIntent);
        }
        else if (Logic.getNumberOfDecimals(mEditTransactionView.getText().toString()) > 2) {
            setResult(RESULT_CANCELED, replyIntent);
        }
        else {
            String quantity = mEditTransactionView.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, this.id);
            replyIntent.putExtra(DOUBLE_REPLY, quantity);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
