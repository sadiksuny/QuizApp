package com.example.lab2.controller;

public class NextQuestion {

    private static int index=0;
    private int total_qs= 0 ;

    public NextQuestion(int total_qs){
        this.total_qs=total_qs;
    }
    public int getCurrentQuestion(){
        return index;
    }

    public int getNextQuestionIndex(){
        index= (index+1)%this.total_qs;
        return index;
    }
}
