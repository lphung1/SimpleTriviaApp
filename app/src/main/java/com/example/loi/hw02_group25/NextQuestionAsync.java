package com.example.loi.hw02_group25;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Loi on 3/4/2018.
 */

public class NextQuestionAsync extends AsyncTask<Question, Void, Void> {

    ImageView triviaImage;
    TextView textQuestion;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    Question question;
    TextView questionNumber;
    Context context;

    public NextQuestionAsync(TextView questionNumber, ImageView triviaImage, TextView textQuestion, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, Context c) {
        this.triviaImage = triviaImage;
        this.textQuestion = textQuestion;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.questionNumber = questionNumber;
        context = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        triviaImage.setImageResource(R.drawable.giphy);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);


        if(question.getImageUrl() != null){
            new ImageDownloaderTask(triviaImage, context).execute(question.getImageUrl());
        }
        else{
            triviaImage.setImageResource(R.drawable.default_question);
        }

        textQuestion.setText(question.getText());
        questionNumber.setText("Q" + (QuestionActivity.position + 1));
        rb1.setText(question.getQuestions(0));
        rb2.setText(question.getQuestions(1));
        rb3.setText(question.getQuestions(2));
        if (rb4 != null) {
            rb4.setText(question.getQuestions(3));
        }
        if(rb4.getText().equals("")){ //if blank remove radio button
            rb4.setVisibility(View.GONE);
        }
        else{
            rb4.setVisibility(View.VISIBLE);
        }



    }

    @Override
    protected Void doInBackground(Question... voids) {

        question = voids[0];


        return null;
    }
}
