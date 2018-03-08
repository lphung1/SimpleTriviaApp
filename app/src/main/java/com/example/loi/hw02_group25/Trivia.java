package com.example.loi.hw02_group25;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Loi on 3/4/2018.
 */

public class Trivia {

    private String id, text, imageUrl, answer = null;
    private JSONArray choices = null;

    public Trivia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuestions(int i) {
        try {
            return choices.get(i).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getChoiceSize(){
        return choices.length();
    }

    public void setChoices(JSONArray choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
