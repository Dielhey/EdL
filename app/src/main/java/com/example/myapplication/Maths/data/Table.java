package com.example.myapplication.Maths.data;

import android.graphics.Path;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;


public class Table {
    private int tableSize;
    private int correctAnswers;
    private char operator;
    private ArrayList<Operation> operations = new ArrayList<Operation>();
    private ArrayList<EditText> answers = new ArrayList<EditText>();

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

    private void generateTable() {
        for (int i = 1; i < 21; i++) {

            Random random = new Random();
            this.operations.add(new Operation(random.nextInt(tableSize) , random.nextInt(tableSize), operator));
        }
    }

    public void verifyAnswers() {
        this.correctAnswers = 0;
        for (int i = 0; i < operations.size(); i++) {
            if(answers.get(i).getText().toString().equals("")) {
                continue;
            }
            System.out.println("answer : " + Integer.parseInt(answers.get(i).getText().toString()));
            System.out.println("réponse : " + operations.get(i).getAnswer());
            if(Integer.parseInt(answers.get(i).getText().toString()) == operations.get(i).getAnswer()) {
                correctAnswers++;
                System.out.println(correctAnswers);
            }
        }
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public int getCorrectAnswers() {
        verifyAnswers();
        return correctAnswers;
    }

    public ArrayList<EditText> getAnswers() {
        return answers;
    }
}


