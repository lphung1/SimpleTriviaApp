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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView percentageCorrect = (TextView) findViewById(R.id.percentageText);

        if (getIntent() != null && getIntent().getExtras() != null) {

            correct = (getIntent().getExtras().getInt("c"));
            total = (MainActivity.triviaArrayList.size());

            Log.d("Number Correct", "" + correct);

            percentageCorrect.setText("" + correct/total);
        }
        else{
            correct = 0;
            total = 0;
            percentageCorrect.setText("0" + "%");
        }



        findViewById(R.id.quitStatsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.tryAgainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Results.this, MainActivity.class));
            }
        });

        findViewById(R.id.statsText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Number Correct", "" + correct);
                Log.d("Total", "" + total);
            }
        });


    }





}
