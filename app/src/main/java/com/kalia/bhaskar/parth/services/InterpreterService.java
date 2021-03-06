package com.kalia.bhaskar.parth.services;

import android.content.Context;
import android.util.Log;

import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;
import com.kalia.bhaskar.parth.interfaces.InterpreterServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;

/**
 * Created by bhaskar on 14/6/16.
 */

/*
* this service interprets whatever user says
* and checks if such a keyword already exists in
* keyword mapping or not
* */

public class InterpreterService implements InterpreterServiceInterface{
    private DataServiceInterface dataService;

    public InterpreterService(){
        dataService = new DataService();
    }

    @Override
    public InterpretedAction interpret(String keyword, Context context){
        InterpretedAction ia = null;

        //code for identifying type of keyword (work or speak)
        if(dataService.getCommandTypeText(keyword,context) != null){ // for only command
            ia = new InterpretedAction(keyword,dataService.getCommandTypeText(keyword,context));
        }else if(dataService.getCommandTypeText(keyword.split(" ")[0],context)!= null){ // for command with argument
            ia = new InterpretedAction(keyword,dataService.getCommandTypeText(keyword.split(" ")[0],context));
        }else{
            return  null;
        }
        return  ia;
    }
}
