package com.example.myapplication.Maths;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.myapplication.EdLActivity;
import com.example.myapplication.FelicitationActivity;
import com.example.myapplication.Maths.data.Operation;
import com.example.myapplication.Maths.data.Table;
import com.example.myapplication.R;

import java.util.ArrayList;


public class MathsQuizActivity extends EdLActivity {

    // Keys
    public static final String NUMBER_KEY = "number_key";
    public static final String OPERATOR_KEY = "operator_key";

    // Data
    private int index = 0;
    private Table table;
    private ArrayList<LinearLayout> questions = new ArrayList<>();


    // Views
    private LinearLayout multiplications, currentLayout;
    private Button btnPrevious, btnNext;
    private TextView tvNb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_quiz);

        multiplications = findViewById(R.id.multiplications);
        table = new Table(getIntent().getIntExtra(NUMBER_KEY, 1), getIntent().getCharExtra(OPERATOR_KEY, '+'));
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        tvNb = findViewById(R.id.tvNb);
        generateQuestions();
        multiplications.addView(questions.get(index));
        currentLayout = questions.get(index);
        tvNb.setText("Question " + (index+1) + "/" + table.getAnswers().size());
        View inputField = currentLayout.findViewById(R.id.template_resultat);
        inputField.requestFocus();

        // Permet d'afficher le clavier automatiquemment
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT);


        Intent intentWin  = new Intent(MathsQuizActivity.this, FelicitationActivity.class);
        // Pour passer à la question précédente
        btnPrevious.setOnClickListener(view -> {
            // Modifie l'affichage à l'écran
            multiplications.removeView(currentLayout);
            index--;
            currentLayout = questions.get(index);
            multiplications.addView(currentLayout);
            tvNb.setText("Question " + (index+1) + "/" + table.getAnswers().size());
            View inputFieldPrev  = currentLayout.findViewById(R.id.template_resultat);
            showKeyboardWithDelay(inputFieldPrev);
            if (index == 0) {
                // Permet de ne pas avoir d'erreur de sortie de liste
                btnPrevious.setEnabled(false);
                btnPrevious.setBackgroundColor(getResources().getColor(R.color.light_gray, getTheme()));
            }
        });

        // Pour passer à la question suivante
        btnNext.setOnClickListener(view -> {
            // Si on est à la dernière question, on vaau résultat
            if (index == table.getOperations().size() - 1) {
                intentWin.putExtra(FelicitationActivity.RESULT_KEY, table.getCorrectAnswers());
                intentWin.putExtra(FelicitationActivity.TOTAL_KEY, index+1);
                intentWin.putExtra(FelicitationActivity.EXERCICE_KEY, FelicitationActivity.MATHS);
                startActivity(intentWin);
            }
            else {
                multiplications.removeView(currentLayout);
                index++;
                currentLayout = questions.get(index);
                multiplications.addView(currentLayout);
                tvNb.setText("Question " + (index+1) + "/" + table.getAnswers().size());
                View inputFieldNext = currentLayout.findViewById(R.id.template_resultat);
                showKeyboardWithDelay(inputFieldNext);
                // Change l'apparence du bouton pour montrer la fin de l'exercice
                if (index == table.getOperations().size() - 1) {
                    btnNext.setText("Valider");
                    btnNext.setTextColor(getResources().getColor(R.color.red, getTheme()));
                }
                // Il faut réactiver le bouton précédent vu qu'on est plus à la première question
                btnPrevious.setEnabled(true);
                btnPrevious.setBackgroundColor(getResources().getColor(R.color.gray, getTheme()));
            }

        });
        // On démarre à la première question, donc le bouton précédent est automatiquemment désactivé
        btnPrevious.setEnabled(false);
        btnPrevious.setBackgroundColor(getResources().getColor(R.color.light_gray, getTheme()));
    }

    // Permet d'afficher le clavier automatiquemment
    // (avec un délai pour ne pas avoir de problème d'affichage lorsqu'on pass trop vite)
    public void showKeyboardWithDelay(View view) {
        view.requestFocus();
        view.post(() -> {

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    // Génére la liste des questions
    public void generateQuestions() {
        int i =0;
        for (Operation operation : table.getOperations()) {
            LinearLayout linearTemp = (LinearLayout) getLayoutInflater().inflate(R.layout.template_mult, null);
            TextView calcul = linearTemp.findViewById(R.id.template_calcul);
            calcul.setText(operation.getNum1() + " " + operation.getOperator() + " " + operation.getNum2() + " =");

            EditText resultat = linearTemp.findViewById(R.id.template_resultat);
            resultat.setHint("?");

            table.getAnswers().put(i, resultat);
            i++;
            questions.add(linearTemp);
        }
    }

}
