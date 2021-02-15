package com.example.lab2.model;

public class Question {
    private int id;
    private String answerIsTrue;

    public Question(int id, String answerIsTrue){
        this.id = id;
        this.answerIsTrue= answerIsTrue;
    }

    public int getQuestionId(){
        return this.id;
    }

    public String answer(){
        return this.answerIsTrue;
    }

}
