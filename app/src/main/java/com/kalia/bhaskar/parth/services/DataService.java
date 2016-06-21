package com.kalia.bhaskar.parth.services;

import android.app.Notification;
import android.content.Context;
import android.util.Log;

import com.kalia.bhaskar.parth.dao.ActionSpeechDao;
import com.kalia.bhaskar.parth.dao.CommandTypeDao;
import com.kalia.bhaskar.parth.dto.ActionSpeechDto;
import com.kalia.bhaskar.parth.dto.CommandTypeDto;
import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;

import java.util.List;

/**
 * Created by bhaskar on 17/6/16.
 */
public class DataService implements DataServiceInterface{

    private static final String TAG = "Data Service";

    @Override
    public String getCommandTypeText(String keyword, Context context) {
        CommandTypeDao dao = new CommandTypeDao(context);
        List<CommandTypeDto> list = dao.getRows(keyword);

        if(list.size() > 0){
            return list.get(0).getType();
        }else{
            Log.d(TAG,"no records in table");
            return null;
        }
    }

    @Override
    public void addFromTeachMeActivity(String keyword, String text,Context context) {
        CommandTypeDao dao1 = new CommandTypeDao(context);
        ActionSpeechDao dao2 = new ActionSpeechDao(context);


        //for command type work only entry to be made in commandtype table
        if(text.equals("*work*")){
            // insert data table1
            CommandTypeDto dto1 = new CommandTypeDto();
            dto1.setKeyword(keyword);
            dto1.setType("work");

            dao1.insertRow(dto1);

        }else{
            // insert data table1
            CommandTypeDto dto1 = new CommandTypeDto();
            dto1.setKeyword(keyword);
            dto1.setType("speak");

            dao1.insertRow(dto1);



            // insert data to table2
            ActionSpeechDto dto2 = new ActionSpeechDto();
            dto2.setKeyword(keyword);
            dto2.setText(text);

            dao2.insertRow(dto2);
        }
    }

    @Override
    public void deleteRowsWithKeyword(String keyword, Context context) {
        ActionSpeechDao dao1 = new ActionSpeechDao(context);
        CommandTypeDao dao2 = new CommandTypeDao(context);

        dao1.removeRow(keyword);
        dao2.removeRow(keyword);
    }

    @Override
    public String getActionSpeechText(String keyword,Context context) {
        ActionSpeechDao dao = new ActionSpeechDao(context);
        List<ActionSpeechDto> list = dao.getRows(keyword);

        if(list.size() > 0){
            return list.get(0).getText();
        }else{
            Log.d(TAG,"no records in table");
            return null;
        }
    }

    @Override
    public List<CommandTypeDto> getCommandTypeRows(Context context) {
        CommandTypeDao dao = new CommandTypeDao(context);
        List<CommandTypeDto> list = dao.getAllRows();
        return list;
    }
}
