package com.example.myapplication.Maths.data;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class Table {
    private int tableSize;
    private int correctAnswers;
    private char operator;
    private List<Operation> operations = new ArrayList<>();
    private HashMap<Integer, EditText> answers = new HashMap<>();

    public Table(char operator) {
        this.tableSize = 1;
        this.operator = operator;
        generateTable();
    }
    public Table(int tableNumber, char operator) {
        this.tableSize = tableNumber;
        this.operator = operator;
        generateTable();
    }

    // Génère les 20 opérations
    private void generateTable() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            operations.add(new Operation(random.nextInt(tableSize) + 1, random.nextInt(tableSize) + 1, operator));

        }
    }

    // Regarde le nombre de bonnes réponses
    public void verifyAnswers() {
        this.correctAnswers = 0;
        for (int i = 0; i < operations.size(); i++) {
            if(answers.get(i).getText().toString().equals("")) {
                continue;
            }
            if(Integer.parseInt(answers.get(i).getText().toString()) == operations.get(i).getAnswer()) {
                correctAnswers++;
            }
        }
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public int getCorrectAnswers() {
        verifyAnswers();
        return correctAnswers;
    }

    public HashMap<Integer, EditText> getAnswers() {
        return answers;
    }

}


