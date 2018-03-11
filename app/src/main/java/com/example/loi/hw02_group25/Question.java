package com.example.loi.hw02_group25;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Question extends AppCompatActivity {

    //int answers [] = new int[MainActivity.triviaArrayList.size()];
    public static int correctAnswers = 0;
    Trivia question = null;
    public static int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle("Trivia Time");

        final ImageView triviaImage = findViewById(R.id.questionImage);
        final TextView textQuestion = findViewById(R.id.questionDescriptionText);
        final TextView timeText = findViewById(R.id.timeLeftText);
        final TextView textQuestionNumber = findViewById(R.id.questionNumberText);
        final RadioButton rb1 = findViewById(R.id.question1);
        final RadioButton rb2 = findViewById(R.id.question2);
        final RadioButton rb3 = findViewById(R.id.question3);
        final RadioButton rb4 = findViewById(R.id.question4);

        question = MainActivity.triviaArrayList.get(position);


        new NextQuestionAsync(textQuestionNumber, triviaImage, textQuestion, rb1, rb2, rb3, rb4).execute(question);

        final CountDownTimer timer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time Left: " + (l/ 1000) + " seconds");
            }

            @Override
            public void onFinish() {
                //intent to take to stats screen
                Intent i = new Intent(Question.this, Results.class);
                startActivity(i);
            }
        }.start();


        findViewById(R.id.QuitQuestionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.NextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup rg = findViewById(R.id.radioGroup);

                Log.d("button", "next button clicked");
                Log.d("Correct Answer this", "is-" + question.getAnswer());
                Log.d("Selected answer", "" + getAnswer(rb1, rb2, rb3, rb4));


                if(position == (MainActivity.triviaArrayList.size() - 1)){

                    Intent i = new Intent(Question.this, Results.class);
                    i.putExtra("c",  correctAnswers);
                    Log.d("CorrectQ", "" + correctAnswers);
                    timer.cancel();
                    startActivity(i);

                }
                else{


                    if(MainActivity.triviaArrayList.get(position).getAnswer().contains(getAnswer(rb1, rb2, rb3, rb4))){
                        correctAnswers+=1;
                        Log.d("Correct!!", "correctAnswers" + correctAnswers);

                    }


                    Log.d("Answers arraylist", "is-" + MainActivity.triviaArrayList.get(position).getAnswer());
                    Log.d("Correct Answers Total", "" + correctAnswers);
                    position += 1;
                    Trivia question = MainActivity.triviaArrayList.get(position);
                    rg.clearCheck();
                    new NextQuestionAsync(textQuestionNumber, triviaImage, textQuestion, rb1, rb2, rb3, rb4).execute(question);
                }


            }
        }); //end next on click


    }//end on create

    //make method to reset, and save answers.

    private String getAnswer(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4 ){

        if(rb1.isChecked()){
            return "1";
        }
        else if(rb2.isChecked()){
            return "2";
        }
        else if(rb3.isChecked()){
            return "3";
        }
        else if (rb4.isChecked()){
            return "4";
        }
        else
            return "";

    }


}//end question class
