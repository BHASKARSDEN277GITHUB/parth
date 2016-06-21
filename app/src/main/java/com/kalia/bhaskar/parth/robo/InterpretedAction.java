package com.kalia.bhaskar.parth.robo;

import com.kalia.bhaskar.parth.services.InterpreterService;

/**
 * Created by bhaskar on 14/6/16.
 */

/*
* this class represent on object having properties keyword and type
* this act as feed to responder service
* */

public class InterpretedAction {
    private String keyword;
    private String type; // create enum for 'types' in future

    public InterpretedAction(String keyword, String type){
        this.keyword = keyword;
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
