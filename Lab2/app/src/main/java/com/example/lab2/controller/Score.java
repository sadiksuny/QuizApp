package com.example.lab2.controller;

import com.example.lab2.model.AllQuestions;

public class Score {

    private final int CORRECT_ANSWER= 10;
    private final int SKIP_QUESTION= -5;
    private int score;

    public Score()
    {
        score= 0;

    }
    AllQuestions allQuestions= new AllQuestions();

    public void correctAnswer(){
        score +=CORRECT_ANSWER;
    }
    public void skipQuestion(){
        score+= SKIP_QUESTION;
    }
    public int getScore(){
        return score;
    }
}
