package com.kalia.bhaskar.parth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class TeachMeActivity extends Activity implements View.OnClickListener {
    private DataServiceInterface dataService;
    private EditText keyword;
    private EditText text;
    private EditText removecommand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teach_me_activity);

        //create data service
        dataService = new DataService();

        keyword = (EditText)findViewById(R.id.teachKeywordEdit);
        text = (EditText)findViewById(R.id.teachTextEdit);
        removecommand = (EditText)findViewById(R.id.teachRemoveEdit);

        Button save = (Button)findViewById(R.id.teachSave);
        save.setOnClickListener(this);

        Button remove = (Button)findViewById(R.id.teachRemoveButton);
        remove.setOnClickListener(this);
    }


    //click listener for views
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.teachSave){
            save();
        }else{
            remove();
        }

    }

    private void save(){
        if(keyword.getText().toString().length()>0 && text.getText().toString().length() >0){
            dataService.addFromTeachMeActivity(keyword.getText().toString(), text.getText().toString(),TeachMeActivity.this);
            Toast.makeText(getApplicationContext(),"Your command has been taught successfully.",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"You left either of the fields empty. Try again!",Toast.LENGTH_LONG).show();
        }

        keyword.setText("");
        text.setText("");
    }

    private  void remove(){

        try{
            if(removecommand.getText().toString().length()>0){
                if(!(removecommand.getText().toString().equals("who built you") || dataService.getCommandTypeText(removecommand.getText().toString(),TeachMeActivity.this).equals("work"))){
                    dataService.deleteRowsWithKeyword(removecommand.getText().toString(),TeachMeActivity.this);
                    Toast.makeText(getApplicationContext(),"Command removed successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"You do not have permissions to remove this command!",Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(getApplicationContext(),"Unable to remove empty command.",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Unable to locate command in db. Please note commands are even space sensitive.",Toast.LENGTH_LONG).show();
        }finally {

        }
        removecommand.setText("");
    }
}
