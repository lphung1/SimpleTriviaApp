package com.example.loi.hw02_group25;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Loi on 3/4/2018.
 */

public class NextQuestionAsync extends AsyncTask<Void, Void, Void> {

    ImageView triviaImage;
    TextView textQuestion;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    Trivia question;

    public NextQuestionAsync(ImageView triviaImage, TextView textQuestion, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, Trivia question) {
        this.triviaImage = triviaImage;
        this.textQuestion = textQuestion;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.question = question;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... voids) {

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
        return null;
    }
}
