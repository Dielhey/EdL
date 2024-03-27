package com.example.myapplication.Maths.data;

public class Operation {
    private int num1;
    private int num2;
    private char operator;

    private int answer;

    public Operation(int num1, int num2, char operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        calculate();
    }
    private void calculate() {
        switch (operator) {
            case '+': this.answer = num1 + num2;break;
            case '-': this.answer = num1 - num2;break;
            case '*': this.answer = num1 * num2;break;
        };
    }

    public int getAnswer() {
        return this.answer;
    }

    public int getNum1(){
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public char getOperator() {
        return operator;
    }
}
