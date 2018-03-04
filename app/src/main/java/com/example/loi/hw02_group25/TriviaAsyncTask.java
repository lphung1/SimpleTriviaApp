package com.example.loi.hw02_group25;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ListView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Loi on 3/4/2018.
 */

public class TriviaAsyncTask extends AsyncTask<String, Integer, ArrayList<Trivia>> {

    public TriviaAsyncTask(Context context, ListView listView, ImageView imageView) {
    }

    @Override
    protected ArrayList<Trivia> doInBackground(String... params) {
        HttpURLConnection connection = null;


        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Trivia> result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


} // end class
