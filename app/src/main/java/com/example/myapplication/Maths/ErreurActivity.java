package com.example.myapplication.Maths;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class ErreurActivity extends AppCompatActivity {

    public static final String RESULT_KEY = "result_key";

    private int nbErrors;
    private Button btnAgain, btnCancel;
    private TextView errors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        btnAgain = findViewById(R.id.btnAgain);
        btnCancel = findViewById(R.id.btnCancel);
        errors = findViewById(R.id.errors);
        nbErrors = getIntent().getIntExtra(RESULT_KEY, 1);
        errors.setText(errors.getText() + Integer.toString(nbErrors));
        btnAgain.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        });

        btnCancel.setOnClickListener(view -> {
            Intent intent = new Intent(ErreurActivity.this, MathsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }



}
