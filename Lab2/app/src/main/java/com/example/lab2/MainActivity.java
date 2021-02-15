package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;

import com.example.lab2.controller.NextQuestion;
import com.example.lab2.controller.Score;
import com.example.lab2.model.AllQuestions;
import com.example.lab2.model.Question;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView questionView = null;
    private TextView scoreView= null;
    private Button trueButton= null;
    private Button falseButton= null;
    private Button nextButton = null;
    private Button summaryButton = null;
    private int total_questions = 4;
    private int questionsViewed= 0 ;


    private final String TAG_INDEX= "Index out of bounds";
    AllQuestions allQuestions= new AllQuestions();
    NextQuestion nextQuestion = new NextQuestion(total_questions);
    Score score= new Score();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        questionView= findViewById(R.id.questionView);
        questionView.setText("Are you ready?");
        questionsViewed++;
        
        scoreView= findViewById(R.id.score_view);
        scoreView.setText("0");
        trueButton= findViewById(R.id.true_button);
        falseButton= findViewById(R.id.false_button);
        nextButton= findViewById(R.id.next_button);
        summaryButton = findViewById(R.id.summary_button);


        trueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index= nextQuestion.getCurrentQuestion();
                Question question= null;
                try {
                    question= allQuestions.getQuestion(index);
                } catch (Exception e){
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (question.answer().equals("True")){
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }
                if (questionsViewed==total_questions){
                    launchSummaryActivity(v);
                }else{
                    questionsViewed++;
                    index= nextQuestion.getNextQuestionIndex();
                }

                questionView.setText(allQuestions.getQuestion(index).getQuestionId());
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index= nextQuestion.getCurrentQuestion();
                Question question= null;
                try {
                    question= allQuestions.getQuestion(index);
                } catch (Exception e){
                    Log.d(TAG_INDEX, "index out of bounds");
                }

                if (question.answer().equals("False")){
                    score.correctAnswer();
                    scoreView.setText(String.valueOf(score.getScore()));
                }

                if (questionsViewed==total_questions){
                    launchSummaryActivity(v);
                }else{
                    questionsViewed++;
                    index= nextQuestion.getNextQuestionIndex();
                }
                questionView.setText(allQuestions.getQuestion(index).getQuestionId());
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int index = nextQuestion.getCurrentQuestion();
                Question question = null;
                try {
                    question= allQuestions.getQuestion(index);
                }catch(Exception e){
                    Log.d(TAG_INDEX, "index out of bounds");
                }
                score.skipQuestion();
                scoreView.setText(String.valueOf(score.getScore()));

                if (questionsViewed==total_questions){
                    launchSummaryActivity(v);
                }else{
                    questionsViewed++;
                    index= nextQuestion.getNextQuestionIndex();
                }
                questionView.setText(allQuestions.getQuestion(index).getQuestionId());



            }
        });

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSummaryActivity(v);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void launchSummaryActivity(View view){
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
        finish();


    }
}