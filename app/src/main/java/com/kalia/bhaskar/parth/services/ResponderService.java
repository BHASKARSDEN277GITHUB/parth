package com.kalia.bhaskar.parth.services;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.kalia.bhaskar.parth.interfaces.InterpreterServiceInterface;
import com.kalia.bhaskar.parth.interfaces.ResponderServiceInterface;
import com.kalia.bhaskar.parth.interfaces.SpeakerServiceInterface;
import com.kalia.bhaskar.parth.interfaces.WorkHandlerServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhaskar on 14/6/16.
 */

/*
* this service will help robo to respond
* to whatever user says (it must be a keyword)
*
* based on keyword it can either speak or perform some other action
* */

public class ResponderService implements ResponderServiceInterface{
    private WorkHandlerServiceInterface workHandlerService;
    private SpeakerServiceInterface speakerService;

    public ResponderService(){
        speakerService = new SpeakerService();
        workHandlerService = new WorkHandlerService();
    }

    @Override
    public void respond(InterpretedAction ia, Context context, TextToSpeech textToSpeech){
            workHandlerService.work(ia,context, textToSpeech);
    }
}
