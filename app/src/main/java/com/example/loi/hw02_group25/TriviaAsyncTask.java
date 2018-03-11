package com.example.loi.hw02_group25;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Loi on 3/4/2018.
 */

public class TriviaAsyncTask extends AsyncTask<String, Integer, ArrayList<Question>> {

    ProgressDialog dialog;
    TextView textView;
    Button button;
    public TriviaAsyncTask(Context context, TextView status, Button button) {

        this.button = button;

        textView = status;

        textView.setText("Loading Trivia");

        button.setEnabled(false);

    }

    @Override
    protected ArrayList<Question> doInBackground(String... params) {
        HttpURLConnection connection = null;
        ArrayList<Question> result = new ArrayList<Question>();

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");


                JSONObject root = new JSONObject(json);

                JSONArray questions = root.optJSONArray("questions"); // root object

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject questionsJSONObject = questions.optJSONObject(i);
                    JSONObject choicesJSONObject = questionsJSONObject.optJSONObject("choices"); //sub objects

                    Question t = new Question();

                    t.setId(questionsJSONObject.optString("id"));
                    t.setText(questionsJSONObject.optString("text"));
                    t.setText(questionsJSONObject.optString("text"));
                    t.setAnswer(choicesJSONObject.optString("answer"));
                    if(questionsJSONObject.optString("image").startsWith("http"))
                    {
                        t.setImageUrl(questionsJSONObject.optString("image"));
                    }
                    t.setQuestions(choicesJSONObject.getJSONArray("choice"));

                    Log.d("JsonName", "" + questionsJSONObject.getString("text"));
                    Log.d("Jsonid", "" + questionsJSONObject.getString("id"));
                    Log.d("JsonChoice", "" + choicesJSONObject.getString("choice"));
                    Log.d("Answer", "" + choicesJSONObject.getString("answer"));


                    result.add(t);

                }
            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }
        return result;

    } // end do background


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Question> result) {
        super.onPostExecute(result);
        MainActivity.triviaArrayList = result;

        textView.setText("Trivia Ready!");
        button.setEnabled(true);


    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }





} // end class
