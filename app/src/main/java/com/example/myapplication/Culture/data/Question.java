package com.example.myapplication.Culture.data;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private String correctAnswer;
    private int selectedButton;
    private List<String> wrongAnswers = new ArrayList<>();

    public Question(String question, List<String> wrongAnswers, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return correctAnswer;
    }

    public List<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
    }

    public int getSelectedButton() {
        return selectedButton;
    }
}
