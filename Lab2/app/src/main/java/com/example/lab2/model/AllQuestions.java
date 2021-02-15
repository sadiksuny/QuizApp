package com.example.lab2.model;

import com.example.lab2.R;

public class AllQuestions {
    private int questionIndex;

    private Question[] allQuestions= {
            new Question(R.string.q_start, "True"),

            new Question(R.string.q_continent, "True"),
            new Question(R.string.q_seas, "False"),
            new Question(R.string.q_add, "False")

    };

    public AllQuestions(){
        questionIndex=0;
    }

    public Question getQuestion(int index){
        index= index % allQuestions.length;
        return allQuestions[index];
    }
}
