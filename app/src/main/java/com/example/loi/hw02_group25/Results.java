package com.example.loi.hw02_group25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Results extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView percentageCorrect = (TextView) findViewById(R.id.percentageText);

        int correct = getIntent().getExtras().getInt("c");

        percentageCorrect.setText("" + correct / MainActivity.triviaArrayList.size());


    }





}
