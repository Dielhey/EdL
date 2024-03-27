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
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Maths.data.Operation;
import com.example.myapplication.Maths.data.Table;
import com.example.myapplication.R;

import java.util.ArrayList;


public class MathsQuizActivity extends AppCompatActivity {

    public static final String NUMBER_KEY = "number_key";
    public static final String OPERATOR_KEY = "operator_key";

    private int index = 0;
    private Table table;

    private LinearLayout multiplications, currentLayout;

    private ArrayList<LinearLayout> questions = new ArrayList<>();
    private Button btnPrevious, btnNext;
    private TextView tvNb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths_quiz);

        multiplications = findViewById(R.id.multiplications);
        //scroll = findViewById(R.id.scroll);
        table = new Table(getIntent().getIntExtra(NUMBER_KEY, 1), getIntent().getCharExtra(OPERATOR_KEY, '+'));
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        tvNb = findViewById(R.id.tvNb);
        generateQuestions();
        multiplications.addView(questions.get(index));
        currentLayout = questions.get(index);
        tvNb.setText("Question " + (index+1) + "/" + table.getAnswers().size());
        View inputField = currentLayout.findViewById(R.id.template_resultat);
        inputField.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT);

        Intent intentLose  = new Intent(MathsQuizActivity.this, ErreurActivity.class);
        Intent intentWin  = new Intent(MathsQuizActivity.this, FelicitationActivity.class);

        ActivityResultLauncher<Intent> result = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                    }
                }
        );
        btnPrevious.setOnClickListener(view -> {
            multiplications.removeView(currentLayout);
            index--;
            currentLayout = questions.get(index);
            multiplications.addView(currentLayout);
            tvNb.setText("Question " + (index+1) + "/" + table.getAnswers().size());
            View inputFieldPrev  = currentLayout.findViewById(R.id.template_resultat);
            showKeyboardWithDelay(inputFieldPrev);
            if (index == 0) {
                btnPrevious.setEnabled(false);
                btnPrevious.setBackgroundColor(getResources().getColor(R.color.light_gray, getTheme()));
            }
        });
        btnNext.setOnClickListener(view -> {
            if (index == table.getOperations().size() - 1) {
                intentWin.putExtra(FelicitationActivity.RESULT_KEY, table.getCorrectAnswers());
                intentWin.putExtra(FelicitationActivity.TABLE_KEY, index+1);
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
                if (index == table.getOperations().size() - 1) {
                    btnNext.setText("Valider");
                    btnNext.setTextColor(getResources().getColor(R.color.red, getTheme()));
                }
                btnPrevious.setEnabled(true);
                btnPrevious.setBackgroundColor(getResources().getColor(R.color.gray, getTheme()));
            }

        });
        btnPrevious.setEnabled(false);
        btnPrevious.setBackgroundColor(getResources().getColor(R.color.light_gray, getTheme()));
    }

    public void showKeyboardWithDelay(View view) {
        view.requestFocus();
        view.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }

    public void generateQuestions() {
        int i =0;
        for (Operation operation : table.getOperations()) {
            LinearLayout linearTemp = (LinearLayout) getLayoutInflater().inflate(R.layout.template_mult, null);
            TextView calcul = (TextView) linearTemp.findViewById(R.id.template_calcul);
            calcul.setText(operation.getNum1() + " " + operation.getOperator() + " " + operation.getNum2() + " =");
            System.out.println(calcul.getText());

            EditText resultat = (EditText) linearTemp.findViewById(R.id.template_resultat);
            resultat.setHint("?");

            table.getAnswers().put(i, resultat);
            i++;
            questions.add(linearTemp);
        }
    }

}
