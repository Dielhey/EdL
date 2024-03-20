package com.example.myapplication.Maths;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class FelicitationActivity extends AppCompatActivity {

    private Button btnTable, btnHome;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_felicitations);

        btnHome = (Button) findViewById(R.id.btnGobackHome);
        btnTable = (Button) findViewById(R.id.btnTable);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(FelicitationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        btnTable.setOnClickListener(view -> {
            Intent intent = new Intent(FelicitationActivity.this, Exercice5Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }


}
