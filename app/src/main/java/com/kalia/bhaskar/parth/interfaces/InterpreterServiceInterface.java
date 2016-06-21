package com.kalia.bhaskar.parth.interfaces;

import android.content.Context;

import com.kalia.bhaskar.parth.robo.InterpretedAction;

/**
 * Created by bhaskar on 15/6/16.
 */
public interface InterpreterServiceInterface {
    public InterpretedAction interpret(String keyword, Context context);
}
