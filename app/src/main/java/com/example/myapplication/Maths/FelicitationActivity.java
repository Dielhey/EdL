package com.example.myapplication.Maths;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import java.text.DecimalFormat;

public class FelicitationActivity extends AppCompatActivity {

    private int correct, total;
    public static final String RESULT_KEY = "result_key";
    public static final String TABLE_KEY = "table_key";
    private Button btnTable, btnHome;
    private TextView tvErrors;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correct = getIntent().getIntExtra(RESULT_KEY, 0);
        total = getIntent().getIntExtra(TABLE_KEY, 0);
        setContentView(R.layout.activity_felicitations);
        tvErrors = findViewById(R.id.tvErrors);
        double percentage = ((float) correct / (float) total) * 100.0;
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedPercentage = df.format(percentage);
        tvErrors.setText("Votre score : " +  correct + "/" + total + " (" + formattedPercentage + "%)");
        btnHome = (Button) findViewById(R.id.btnGobackHome);
        btnTable = (Button) findViewById(R.id.btnTable);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(FelicitationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        btnTable.setOnClickListener(view -> {
            Intent intent = new Intent(FelicitationActivity.this, MathsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }


}
