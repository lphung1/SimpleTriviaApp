package com.example.loi.hw02_group25;

import android.content.Context;
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
    public int position = 0;
    Context context = Question.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle("Trivia Time");

        final ImageView triviaImage = findViewById(R.id.questionImage);
        final TextView textQuestion = findViewById(R.id.questionDescriptionText);
        final TextView timeText = findViewById(R.id.timeLeftText);
        final RadioGroup rg = findViewById(R.id.radioGroup);
        final RadioButton rb1 = findViewById(R.id.question1);
        final RadioButton rb2 = findViewById(R.id.question2);
        final RadioButton rb3 = findViewById(R.id.question3);
        final RadioButton rb4 = findViewById(R.id.question4);
        final CountDownTimer timer;

        question = MainActivity.triviaArrayList.get(position);


        new NextQuestionAsync(triviaImage, textQuestion, rb1, rb2, rb3, rb4).execute(question);

        new CountDownTimer(120000, 1000) {
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


                Log.d("correct answer", question.getAnswer() );

                if(position == (MainActivity.triviaArrayList.size() - 1)){

                    Intent i = new Intent(getApplicationContext(), Results.class);
                    i.putExtra("c", (int) correctAnswers);
                    Log.d("Number of correct answers", "" + correctAnswers);
                    startActivity(i);


                }


                    if (question.getAnswer().equals(getAnswer(rb1, rb2, rb3, rb4))) {
                        correctAnswers += 1;
                        Log.d("correct!!!", question.getAnswer().toString());

                    }
                    Log.d("Answers", "" + question.getAnswer());

                    position += 1;
                    Trivia question = MainActivity.triviaArrayList.get(position);
                    rg.clearCheck();
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

                if (question.getChoiceSize() > 3) {
                    rb4.setText(question.getQuestions(3));
                }

                if(rb4.getText().equals("")){ //if blank remove radio button
                    rb4.setVisibility(View.GONE);
                }
                else{
                    rb4.setVisibility(View.VISIBLE);
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
