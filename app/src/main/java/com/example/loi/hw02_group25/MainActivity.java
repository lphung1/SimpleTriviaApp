package com.example.loi.hw02_group25;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Trivia> triviaArrayList = new ArrayList<Trivia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome To Trivia");
        final TextView textView = findViewById(R.id.readyStatusText);
        final Button button = findViewById(R.id.startTriviaButton);

        if(isConnected()){
            new TriviaAsyncTask(MainActivity.this, textView, button).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        }
        else{
            Toast.makeText(MainActivity.this, "Not connected to internet", Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.startTriviaButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Question.class);

                startActivity(intent);

            }
        }); //end button on click listener


        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //click on image to reload trivia
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()){
                    new TriviaAsyncTask(MainActivity.this, textView, button).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
                }
                else{
                    Toast.makeText(MainActivity.this, "Not connected to internet", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    } //end isConnected method


}//end main activity
