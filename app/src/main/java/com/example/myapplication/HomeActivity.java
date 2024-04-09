package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Culture.data.CultureQuizActivity;
import com.example.myapplication.Maths.MathsActivity;
import com.example.myapplication.Maths.MathsQuizActivity;
import com.example.myapplication.db.DatabaseClient;
import com.example.myapplication.db.User;

import java.util.List;

public class HomeActivity extends EdLActivity {


    // Data
    private DatabaseClient mDb;
    private Intent mathsIntent, cultureIntent;

    @Override
    protected void onStart() {
        super.onStart();
        // Création des Intents
        mathsIntent = new Intent(this, MathsActivity.class);
        cultureIntent = new Intent(this, CultureQuizActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDb = DatabaseClient.getInstance(getApplicationContext());

    }

    /**
     * Les différents événements de choix d'exercice
     * @param view
     */
    public void onAddition(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '+');
        startActivity(mathsIntent);
    }

    public void onMultiplication(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '*');
        startActivity(mathsIntent);
    }

    public void onSubstraction(View view) {
        mathsIntent.putExtra(MathsActivity.OPERATOR_KEY, '-');
        startActivity(mathsIntent);
    }

    public void onCulture(View view) {
        startActivity(cultureIntent);
    }
}