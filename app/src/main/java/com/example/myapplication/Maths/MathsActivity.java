package com.example.myapplication.Maths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MathsActivity extends AppCompatActivity {


    public static final String OPERATOR_KEY = "operator_key";

    private int tableNumber;
    private Button btnLv1, btnLv2, btnLv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_maths);
        btnLv1 = findViewById(R.id.btnLv1);
        btnLv2 = findViewById(R.id.btnLv2);
        btnLv3 = findViewById(R.id.btnLv3);
        Intent intent  = new Intent(MathsActivity.this, MathsQuizActivity.class);
        intent.putExtra(MathsQuizActivity.OPERATOR_KEY, getIntent().getCharExtra(OPERATOR_KEY, '+'));
        ActivityResultLauncher<Intent> tableMultiplication = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        String message;
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            message = "Changement de table";
                        }
                        else {
                            message = "Bouton back";
                        }
                        Toast.makeText(MathsActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        btnLv1.setOnClickListener(view -> {
            tableNumber = 5;
            intent.putExtra(MathsQuizActivity.NUMBER_KEY, tableNumber);
            tableMultiplication.launch(intent);
        });
        btnLv2.setOnClickListener(view -> {
            tableNumber = 10;
            intent.putExtra(MathsQuizActivity.NUMBER_KEY, tableNumber);
            tableMultiplication.launch(intent);
        });
        btnLv3.setOnClickListener(view -> {
            tableNumber = 20;
            intent.putExtra(MathsQuizActivity.NUMBER_KEY, tableNumber);
            tableMultiplication.launch(intent);
        });

    }
}
