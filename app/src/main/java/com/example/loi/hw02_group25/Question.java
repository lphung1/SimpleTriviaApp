package com.example.loi.hw02_group25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setTitle("Trivia Time");
        ImageView triviaImage = findViewById(R.id.questionImage);
        TextView textQuestion = findViewById(R.id.questionDescriptionText);
        RadioButton rb1 = findViewById(R.id.question1);
        RadioButton rb2 = findViewById(R.id.question2);
        RadioButton rb3 = findViewById(R.id.question3);
        RadioButton rb4 = findViewById(R.id.question4);

        int position = 0;
        Trivia question = MainActivity.triviaArrayList.get(position);
        if(question.getImageUrl() != null){
            new ImageDownloaderTask(triviaImage).execute(question.getImageUrl());
        }

        textQuestion.setText(question.getText());
        rb1.setText(question.getQuestions(0));
        rb2.setText(question.getQuestions(1));
        rb3.setText(question.getQuestions(2));
        rb4.setText(question.getQuestions(3));



        findViewById(R.id.QuitQuestionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.NextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("imageUrl","" + MainActivity.triviaArrayList.get(0).getImageUrl());
            }
        });


    }
}
