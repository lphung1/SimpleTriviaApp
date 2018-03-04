package com.example.loi.hw02_group25;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Question extends AppCompatActivity {

    public static int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle("Trivia Time");

        final ImageView triviaImage = findViewById(R.id.questionImage);
        final TextView textQuestion = findViewById(R.id.questionDescriptionText);
        final TextView timeText = findViewById(R.id.timeLeftText);
        final RadioButton rb1 = findViewById(R.id.question1);
        final RadioButton rb2 = findViewById(R.id.question2);
        final RadioButton rb3 = findViewById(R.id.question3);
        final RadioButton rb4 = findViewById(R.id.question4);

        Trivia question = MainActivity.triviaArrayList.get(position);
        new NextQuestionAsync(triviaImage, textQuestion, rb1, rb2, rb3, rb4, question ).execute();

        final CountDownTimer timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time Left: " + (l/ 1000) + " seconds");
            }

            @Override
            public void onFinish() {
                //intent to take to stats screen
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

                position += 1;
                Trivia question = MainActivity.triviaArrayList.get(position);

                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);

                if(question.getImageUrl() != null){
                    new ImageDownloaderTask(triviaImage).execute(question.getImageUrl());
                }
                else{
                    triviaImage.setImageResource(R.drawable.default_question);
                }

                textQuestion.setText(question.getText());
                rb1.setText(question.getQuestions(0));
                rb2.setText(question.getQuestions(1));
                rb3.setText(question.getQuestions(2));
                rb4.setText(question.getQuestions(3));


            }
        }); //end next on click


    }//end on create

    //make method to reset, and save answers.

}//end question class
