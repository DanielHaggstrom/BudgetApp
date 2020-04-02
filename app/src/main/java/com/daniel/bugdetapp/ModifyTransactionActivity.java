package com.daniel.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ModifyTransactionActivity extends AppCompatActivity {

    private EditText mEditTransactionView;
    public static final String EXTRA_REPLY =
            "com.example.android.bugdetapp.REPLY-MODIFY";
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_transaction);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Log.d("update", "ModifyTransactionActivity " + key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
            }
        }
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
            replyIntent.putExtra("ID", this.id);
            replyIntent.putExtra("AMOUNT", quantity);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
