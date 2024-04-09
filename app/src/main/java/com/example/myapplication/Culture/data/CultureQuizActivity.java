package com.example.myapplication.Culture.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.FelicitationActivity;
import com.example.myapplication.R;

public class CultureQuizActivity extends AppCompatActivity {

    // Data
    private CultureAdapter adapter;
    private Quiz quiz = new Quiz();

    // Views
    private ListView lvQuiz;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_quiz);
        btnConfirm = findViewById(R.id.btnConfirm);
        lvQuiz = findViewById(R.id.lvQuiz);
        // La plupart de l'activité se fait dans l'adapter, il n'y a donc pas grand chose à faire ici
        adapter = new CultureAdapter(this, quiz, lvQuiz);
        lvQuiz.setAdapter(adapter);

        // Fin du QCM
        btnConfirm.setOnClickListener(v -> {
            quiz.checkResult();
            Intent intent = new Intent(this, FelicitationActivity.class);
            intent.putExtra(FelicitationActivity.RESULT_KEY, quiz.getCorrectAnswers());
            intent.putExtra(FelicitationActivity.TOTAL_KEY, quiz.getQuestions().size());
            intent.putExtra(FelicitationActivity.EXERCICE_KEY, FelicitationActivity.CULTURE);
            startActivity(intent);
        });
    }


}