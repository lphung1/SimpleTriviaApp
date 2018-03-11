package com.example.loi.hw02_group25;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    int correct, total = 0;
    int percent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ProgressBar pb = findViewById(R.id.percentageBar);

        TextView percentageCorrect = (TextView) findViewById(R.id.percentageText);

        if (getIntent() != null && getIntent().getExtras() != null) { //getting data using intent

            Log.d("Intent recieved", "Extra =" + getIntent().getExtras().getInt("c"));
            correct = (getIntent().getExtras().getInt("c"));
            total = (MainActivity.triviaArrayList.size());
            percent = (correct * 100) / total;

            percentageCorrect.setText(  percent + "%" );

            pb.setMax(total);
            pb.setProgress(correct);
        }
        else{ //different way of getting data, using global variable, not ideal
            Log.d("Intent not received", "Correct= " + QuestionActivity.correctAnswers);
            correct = QuestionActivity.correctAnswers;
            total = MainActivity.triviaArrayList.size();
            percent = (correct * 100) / total;
            percentageCorrect.setText(percent + "%");
            pb.setMax(total);
            pb.setProgress(correct);
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
                startActivity(new Intent(Results.this, QuestionActivity.class));
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
