package com.example.myapplication.Culture.data;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Un adapter pour stocker toutes les questions du QCM
 */
public class CultureAdapter extends ArrayAdapter<Question> {

    private ListView listView;
    private Quiz quiz;
    private int[] btn_id = {R.id.btnAnswer1, R.id.btnAnswer2, R.id.btnAnswer3, R.id.btnAnswer4};
    private Button[][] btns;


    boolean[][] buttonSelected;

    public CultureAdapter(Context mCtx, Quiz quiz, ListView lvQuiz) {
        super(mCtx, R.layout.template_question, quiz.getQuestions());
        this.quiz = quiz;
        this.listView = lvQuiz;
        btns = new Button[quiz.getQuestions().size()][4];
        buttonSelected = new boolean[quiz.getQuestions().size()][4];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Question question = getItem(position);
        // Permet de garder les boutons sélectionnés lorsque l'on ne les voit plus dans la ListView
        if(question.getSelectedButton() == 0) {
            Arrays.fill(buttonSelected[position], false);
        }

        // Inflation du template
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View questionView = inflater.inflate(R.layout.template_question, parent, false);
        TextView tvQuestion = questionView.findViewById(R.id.tvQuestion);
        tvQuestion.setText(question.getQuestion());

        // Réponses mises dans un ordre aléatoire
        List<String> answers = question.getWrongAnswers();
        List<String> shuffledAnswers = new ArrayList<>(answers);
        shuffledAnswers.add(question.getAnswer());
        Collections.shuffle(shuffledAnswers);

        // Instantiation de chaque boutons avec une sélection dynamique
        for (int i = 0; i < btns[position].length; i++) {
            int index = i;
            btns[position][i] = questionView.findViewById(btn_id[i]);
            btns[position][i].setOnClickListener(v -> {
                question.setSelectedButton(index + 1);
                quiz.addAnswer(position, (Button) v);
                updateButtonStates(position, index);
                listView.smoothScrollToPosition(position + 1);
            });
            btns[position][i].setBackgroundColor(buttonSelected[position][i]
                    ? getContext().getColor(R.color.buttonPressed)
                    : getContext().getColor(R.color.buttons));
            btns[position][i].setText(shuffledAnswers.get(i));
        }

        return questionView;
    }

    // Change le fond pour le bouton sélectionné
    private void updateButtonStates(int position, int index) {
        for (int i = 0; i < btns[position].length; i++) {
            buttonSelected[position][i] = i == index;
            btns[position][i].setBackgroundColor(buttonSelected[position][i]
                    ? getContext().getColor(R.color.buttonPressed)
                    : getContext().getColor(R.color.buttons));
        }
    }
}
