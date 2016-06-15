package com.kalia.bhaskar.parth.services;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;

import com.kalia.bhaskar.parth.interfaces.WorkHandlerServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;
import com.kalia.bhaskar.parth.robo.Mappings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhaskar on 15/6/16.
 */
public class WorkHandlerService implements WorkHandlerServiceInterface {

    SpeakerService speakerService;
    private Map<String, String> keywordToTextMap;

    public WorkHandlerService(){
        speakerService = new SpeakerService();
        keywordToTextMap = new Mappings().getKeywordToTextMap();
        //generateMap();
    }

    /*private void generateMap(){
        *//*
        * in future generate this map from file
        * map : {keyword:text to speak}
        * *//*

        keywordToTextMap = new HashMap<String, String>();
        keywordToTextMap.put("hello","hello beautiful");
        keywordToTextMap.put("what is your name","my name is robo Arjun");
        keywordToTextMap.put("who is your owner","my owner is  bhaskar kalia");
    }*/

    /*
    * here comes the methods for doing work
    * */
    private void sleep(Context context){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    @Override
    public void work(InterpretedAction ia, Context context, TextToSpeech textToSpeech) {
        /*
        * use cases for calling respective methods for work based upon keyword
        * */

        try{
            if(ia.getType().equals("speak")){
                speakerService.speak(keywordToTextMap.get(ia.getKeyword()),textToSpeech);
            }else{
                switch (ia.getKeyword()){
                    case "sleep":
                        sleep(context);
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            speakerService.speak("sorry I am dumb right now",textToSpeech);
        }

    }
}