package com.kalia.bhaskar.parth.interfaces;

import android.content.Context;

import com.kalia.bhaskar.parth.robo.InterpretedAction;

/**
 * Created by bhaskar on 14/6/16.
 */
public interface RoboInterface {
    //basic robo behaviours
    public void respond(InterpretedAction ia, Context context);
    public void interpret(String keyword, Context context);
}
