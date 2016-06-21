package com.kalia.bhaskar.parth.services;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;

import com.kalia.bhaskar.parth.activities.CommandsListActivity;
import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;
import com.kalia.bhaskar.parth.interfaces.SpeakerServiceInterface;
import com.kalia.bhaskar.parth.interfaces.WorkHandlerServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;

/**
 * Created by bhaskar on 15/6/16.
 */
public class WorkHandlerService implements WorkHandlerServiceInterface {

    private SpeakerServiceInterface speakerService;
    private DataServiceInterface dataService;

    public WorkHandlerService(){
        speakerService = new SpeakerService();
        dataService = new DataService();
    }

    @Override
    public void work(InterpretedAction ia, Context context, TextToSpeech textToSpeech) {

        try{
            if(ia.getType().equals("speak")){
                 speakerService.speak(dataService.getActionSpeechText(ia.getKeyword(),context),textToSpeech);
            }else{
                switch (ia.getKeyword()){
                    case "sleep":
                        sleep(context);
                        break;
                    case "show commands":
                        showCommands(context);
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            speakerService.speak("sorry I am little down right now",textToSpeech);
        }

    }

    /* methods for doing work */
    private void sleep(Context context){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void showCommands(Context context){
        Intent i = new Intent(context, CommandsListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    /* /methods for doing work */
}
