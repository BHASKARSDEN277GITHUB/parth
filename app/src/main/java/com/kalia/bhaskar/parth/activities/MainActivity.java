package com.kalia.bhaskar.parth.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.kalia.bhaskar.parth.R;
import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;
import com.kalia.bhaskar.parth.robo.Robo;
import com.kalia.bhaskar.parth.services.DataService;


public class MainActivity extends Activity implements OnClickListener {

    private final int REQUEST_OK = 1;
    private Robo robo ; //robo remote
    private DataServiceInterface dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //create new robo instance
        robo = new Robo(getApplicationContext());

        //create data service
        dataService = new DataService();

        findViewById(R.id.getaudio).setOnClickListener(this);
        findViewById(R.id.teachMeButton).setOnClickListener(this);
    }


    //click listener for views
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.getaudio){
            listen();
        }else {
            teachMe();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_OK && resultCode == RESULT_OK) {
            ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            robo.interpret(speech.get(0),getApplicationContext());
        }
    }

    private void listen(){
        /*
        * Listen what user says using RecongnizerIntent
        * */
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            //this will return result from the called activity which
            //can be captured at onActivityResult method
            startActivityForResult(i, REQUEST_OK);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error initializing Robo's text to speech engine!!", Toast.LENGTH_LONG).show();
        }
    }

    private void teachMe(){
        Intent i = new Intent(getApplicationContext(),TeachMeActivity.class);
        startActivity(i);
    }
}