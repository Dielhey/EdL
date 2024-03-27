package com.example.myapplication.Maths.data;

import android.graphics.Path;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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

    private void generateTable() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            operations.add(new Operation(random.nextInt(tableSize) + 1, random.nextInt(tableSize) + 1, operator));

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

    public int getTableSize(){
        return tableSize;
    }
}


