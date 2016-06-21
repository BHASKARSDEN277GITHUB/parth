package com.kalia.bhaskar.parth.dao;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kalia.bhaskar.parth.db.DatabaseManager;
import com.kalia.bhaskar.parth.dto.ActionSpeechDto;
import com.kalia.bhaskar.parth.dto.CommandTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhaskar on 17/6/16.
 */
public class ActionSpeechDao {
    private static final String TAG = "ActionSpeechDao";
    private DatabaseManager dbManager;

    public ActionSpeechDao(Context context){
        dbManager = new DatabaseManager(context);
        Log.d(TAG, "dbManager created successfully");
    }

    //method to get return list of rows based on keyword
    public List<ActionSpeechDto> getRows(String keyword){
        ActionSpeechDto dto = null;
        List<ActionSpeechDto> list = new ArrayList<ActionSpeechDto>();

        //write query for getting speech based upon keyword
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();
        Log.d(TAG,"Version Database: "+sqLiteDatabase.getVersion());

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT keyword, text FROM "+dbManager.TABLE_ACTIONSPEECH+" WHERE keyword = ?", new String[]{ keyword });

        try {
            while (cursor.moveToNext()) {
                dto = new ActionSpeechDto();
                dto.setText(cursor.getString(cursor.getColumnIndex(dbManager.TEXT)));
                dto.setKeyword(cursor.getString(cursor.getColumnIndex(dbManager.KEYWORDAS)));

                list.add(dto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
            sqLiteDatabase.close();
        }

        return list;
    }

    //method to insert record into actionspeech table
    public void insertRow(ActionSpeechDto dto){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbManager.KEYWORDAS,dto.getKeyword());
        values.put(dbManager.TEXT, dto.getText());

        try{
            long count = sqLiteDatabase.insert(dbManager.TABLE_ACTIONSPEECH, null, values);
            Log.d(TAG,count+"");
            if(count == -1){
                values = new ContentValues();
                values.put(dbManager.TEXT,dto.getText());
                sqLiteDatabase.update(dbManager.TABLE_ACTIONSPEECH,values, dbManager.KEYWORDAS+"="+"'"+dto.getKeyword()+"'",null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqLiteDatabase.close();
        }
    }

    //method to remove record from actionspeech table
    public void removeRow(String keyword){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();
        String whereClause = dbManager.KEYWORDAS + " = ?";
        String[] whereArgs = { keyword };
        sqLiteDatabase.delete(dbManager.TABLE_ACTIONSPEECH, whereClause, whereArgs);

        sqLiteDatabase.close();
    }
}
