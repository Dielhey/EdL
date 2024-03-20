package com.example.myapplication.Maths;

import android.content.Intent;
import android.os.Bundle;
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



public class TableMathsActivity extends AppCompatActivity {

    public static final String NUMBER_KEY = "number_key";

    private Table table;

    private LinearLayout multiplications;
    private ArrayList<EditText> answers = new ArrayList<>();
    //private ScrollView scroll;
    private Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_maths);

        multiplications = findViewById(R.id.multiplications);
        //scroll = findViewById(R.id.scroll);
        table = new Table(getIntent().getIntExtra(NUMBER_KEY, 1), '+');
        btnFinish = findViewById(R.id.btnResultat);
        generateQuestions();
        Intent intentLose  = new Intent(TableMathsActivity.this, ErreurActivity.class);
        Intent intentWin  = new Intent(TableMathsActivity.this, FelicitationActivity.class);

        ActivityResultLauncher<Intent> result = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                    }
                }
        );
        btnFinish.setOnClickListener(view -> {
            if (table.getCorrectAnswers() == table.getOperations().size()) {
                result.launch(intentWin);
            }
            else {
                intentLose.putExtra(ErreurActivity.RESULT_KEY, table.getOperations().size() - table.getCorrectAnswers());
                result.launch(intentLose);
            }
        });

    }
    public void generateQuestions() {
        int i =0;
        for (Operation operation : table.getOperations()) {
            LinearLayout linearTemp = (LinearLayout) getLayoutInflater().inflate(R.layout.template_mult, null);
            TextView calcul = (TextView) linearTemp.findViewById(R.id.template_calcul);
            calcul.setText(operation.getNum1() + " " +  operation.getOperator() + " " + operation.getNum2() + " =");
            System.out.println(calcul.getText());

            EditText resultat = (EditText) linearTemp.findViewById(R.id.template_resultat);
            resultat.setHint("?");

            table.getAnswers().add(resultat);

            multiplications.addView(linearTemp);

        }
    }

}
