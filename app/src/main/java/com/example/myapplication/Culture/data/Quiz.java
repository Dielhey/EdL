package com.example.myapplication.Culture.data;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {

    private Map<Integer, Button> answers = new HashMap<>();
    private List<Question> questions = new ArrayList<>();
    private int correctAnswers;

    public Quiz() {
        generateQuestions();
    }

    private void generateQuestions() {
        // Création des questions de grammaire (Source : https://www.lumni.fr/quiz/quiz-reviser-les-notions-de-grammaire)
        String questions = "Trouve le mot manquant : « Tout à coup, un … retentit au fond de la forêt. »|" +
                "Trouve le mot manquant : « Celui qui bouge sans arrêt les ........... est inquiet. »|" +
                "Trouve le mot manquant : « Des  …  seront utiles avant de partir. »|" +
                "Trouve le mot manquant : « C’est peut être quelqu’un qui … pour demander du secours. »|" +
                "Trouve la fonction du groupe de mots entouré d'un astérisque : « Ce matin, *ta sœur* t’a écrit. »|" +
                "Trouve la fonction du groupe de mots entouré d'un astérisque : « Jean a travaillé *toute la nuit*. »|" +
                "Trouve la fonction du groupe de mots entouré d'un astérisque : « Anne semble *fatiguée*. »|" +
                "Trouve la fonction du groupe de mots entouré d'un astérisque : « Les autobus ont *la priorité*. »|" +
                "Trouve la fonction du groupe de mots entouré d'un astérisque : « Avec la nuit commence *l’inquiétude*.»|" +
                "Quelle est la forme négative de : « Nous nous sommes déjà rencontrés » ?|" +
                "Quelle est la forme négative de : « Une hésitation est encore possible » ?|" +
                "Dans la liste ci-dessous, une phrase n'est pas à la forme négative. Laquelle ?";
        String wrongAnswers = "appelle;appellent;appelles|" +
                "bouche;posture;main|" +
                "conseil;conseillent;conseille|" +
                "cri;cries;crient|" +
                "attribut du sujet; complément d'objet direct;complément d'objet indirect|" +
                "sujet;attribut du sujet;complément d'objet direct|" +
                "sujet;complément d'objet direct;complément circonstenciel|" +
                "attribut du sujet;complément circonstenciel;sujet|" +
                "attribut du sujet;complément circonstenciel;complément d'objet direct|" +
                "Nous ne nous sommes pas rencontrés.;On s'est jamais rencontré.;Nous ne nous sommes plus rencontrés.|" +
                "Une hésitation ne sera pas possible;Une hésitation n'est pas possible;Une hésitation n'est jamais possible|" +
                "Les clients n'ont plus envie de quitter le magasin;Sur ce parking, il n'a guère d'endroit de se garer;La ville n'a jamais été plus bruyante.";
        String rightAnswers = "appel|" +
                "doigts|" +
                "conseils|" +
                "crie|" +
                "sujet|" +
                "complément circonstenciel|" +
                "attribut du sujet|" +
                "complément d'objet direct|" +
                "sujet inversé|" +
                "Nous ne nous sommes jamais rencontrés.|" +
                "Une hésitation n'est plus possible.|" +
                "L'orage éclate brusquemment.";
        // Transformation en liste
        List<String> questionList = Arrays.asList(questions.split("\\|"));
        List<String> wrongList = Arrays.asList(wrongAnswers.split("\\|"));
        List<String> rightList = Arrays.asList(rightAnswers.split("\\|"));
        for (int i = 0; i < questionList.size(); i++) {
            List<String> wrongForQuestion = Arrays.asList(wrongList.get(i).split(";"));
            Question question = new Question(questionList.get(i), wrongForQuestion, rightList.get(i));
            this.questions.add(question);
        }
        Collections.shuffle(this.questions);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addAnswer(int index, Button btn) {
        answers.put(index, btn);
    }

    private boolean checkAnswer(int index) {
        // Permet de ne pas avoir d'erreur dans la vérification
        if (!answers.containsKey(index)) {
            return false;
        }
        String answerForIndex = questions.get(index).getAnswer();
        String answerFromPlayer = answers.get(index).getText().toString();
        return answerFromPlayer.equals(answerForIndex);
    }


    /**
     * Regarde le bon nombre de réponse
     */
    public void checkResult() {
        for(int i = 0; i < questions.size(); i++) {
            correctAnswers += (checkAnswer(i))?1:0;
        }
    }
    public int getCorrectAnswers() {
        return correctAnswers;
    }

}
