package com.kalia.bhaskar.parth.services;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.kalia.bhaskar.parth.interfaces.ResponderServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhaskar on 14/6/16.
 */

/*
* this service will help robo to respond
* to whatever user says (it must be a keyword though)
*
* based on keyword it can either speak or perform some other action (such as calling someone etc)
* */

public class ResponderService implements ResponderServiceInterface{
    private WorkHandlerService workHandlerService;
    private SpeakerService speakerService;

    public ResponderService(){
        speakerService = new SpeakerService();
        workHandlerService = new WorkHandlerService();
    }

    @Override
    public void respond(InterpretedAction ia,Context context,TextToSpeech textToSpeech){
            workHandlerService.work(ia,context, textToSpeech);
    }
}
