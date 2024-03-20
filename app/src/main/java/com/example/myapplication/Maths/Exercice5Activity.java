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

public class Exercice5Activity extends AppCompatActivity {

    private NumberPicker number;
    private Button btnChoose;
    private int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_exercice5);
        number = findViewById(R.id.number);
        number.setMinValue(0);
        number.setMaxValue(10);
        btnChoose = findViewById(R.id.btnChoose);
        Intent intent  = new Intent(Exercice5Activity.this, TableMathsActivity.class);
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
                            number.setValue(0);
                        }
                        Toast.makeText(Exercice5Activity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        btnChoose.setOnClickListener(view -> {
            tableNumber = number.getValue();
            intent.putExtra(TableMathsActivity.NUMBER_KEY, tableNumber);
            tableMultiplication.launch(intent);
        });

    }
}
