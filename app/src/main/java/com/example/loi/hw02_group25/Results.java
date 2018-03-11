package com.example.loi.hw02_group25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    int correct, total = 0;
    float percent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView percentageCorrect = (TextView) findViewById(R.id.percentageText);

        if (getIntent() != null && getIntent().getExtras() != null) {

            Log.d("Intent recieved", "Extra =" + getIntent().getExtras().getInt("c"));
            correct = (getIntent().getExtras().getInt("c"));
            total = (MainActivity.triviaArrayList.size());
            percent = (correct * 100) / total;

            percentageCorrect.setText(  percent + "&" );
        }
        else{
            Log.d("Intent not received", "Correct= " + Question.correctAnswers);
            correct = Question.correctAnswers;
            total = MainActivity.triviaArrayList.size();
            percent = (correct * 100) / total;
            percentageCorrect.setText(percent + "%");
        }



        findViewById(R.id.quitStatsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Results.this, MainActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.tryAgainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, Question.class));
            }
        });

        findViewById(R.id.statsText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Number Correct", "" + correct);
                Log.d("Total", "" + total);
                Log.d("Percent","" + percent);
            }
        });



    }





}
