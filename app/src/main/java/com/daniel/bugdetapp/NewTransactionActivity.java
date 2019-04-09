package com.daniel.bugdetapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.bugdetapp.REPLY";

    private EditText mEditTransactionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        mEditTransactionView = findViewById(R.id.editText);

        final Button button = findViewById(R.id.save_to_database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditTransactionView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String quantity = mEditTransactionView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, quantity);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
