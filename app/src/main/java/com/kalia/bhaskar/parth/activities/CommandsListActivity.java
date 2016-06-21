package com.kalia.bhaskar.parth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kalia.bhaskar.parth.R;
import com.kalia.bhaskar.parth.dto.CommandTypeDto;
import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;
import com.kalia.bhaskar.parth.robo.Robo;
import com.kalia.bhaskar.parth.services.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaskar on 18/6/16.
 */
public class CommandsListActivity extends Activity  {
    private DataServiceInterface dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.command_list_activity);

        //create data service
        dataService = new DataService();

        TextView commandBox = (TextView) findViewById(R.id.commandsText);

        String commands = "";

        List<CommandTypeDto> list = dataService.getCommandTypeRows(CommandsListActivity.this);

        for(int i=0;i<list.size();i++){
            commands += "#"+list.get(i).getKeyword() +"\n";
        }

        commandBox.setMovementMethod(new ScrollingMovementMethod());
        commandBox.setText(commands);
    }

}
