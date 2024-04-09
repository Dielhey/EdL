package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Maths.MathsActivity;

import java.text.DecimalFormat;

public class FelicitationActivity extends AppCompatActivity {

    // Constantes
    public static final int MATHS = 1;
    public static final int CULTURE = 2;
    // Keys
    public static final String RESULT_KEY = "result_key";
    public static final String TOTAL_KEY = "table_key";
    public static final String EXERCICE_KEY = "exercice_key";
    // Data
    private int correct, total, exercice;

    // Views
    private Button btnTable, btnHome;
    private TextView tvErrors;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        correct = getIntent().getIntExtra(RESULT_KEY, 0);
        total = getIntent().getIntExtra(TOTAL_KEY, 0);
        exercice = getIntent().getIntExtra(EXERCICE_KEY, 0);
        setContentView(R.layout.activity_felicitations);
        tvErrors = findViewById(R.id.tvErrors);
        // Affiche le pourcentage correctement (deux chiffres après la virgule
        double percentage = ((float) correct / (float) total) * 100.0;
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedPercentage = df.format(percentage);
        tvErrors.setText("Votre score : " +  correct + "/" + total + " (" + formattedPercentage + "%)");
        btnHome = findViewById(R.id.btnGobackHome);
        btnTable = findViewById(R.id.btnTable);
        // Revient au choix de difficulté, pas utile en dehors des maths
        if (exercice == MATHS) {
            btnTable.setOnClickListener(view -> {
                Intent intent = new Intent(FelicitationActivity.this, MathsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            });
        }
        else {
            btnTable.setVisibility(View.GONE);
        }

        // Revient au menu
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(FelicitationActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });



    }


}
