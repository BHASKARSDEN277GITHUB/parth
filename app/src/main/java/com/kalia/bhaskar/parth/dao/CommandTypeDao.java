package com.kalia.bhaskar.parth.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kalia.bhaskar.parth.db.DatabaseManager;
import com.kalia.bhaskar.parth.dto.ActionSpeechDto;
import com.kalia.bhaskar.parth.dto.CommandTypeDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bhaskar on 17/6/16.
 */
public class CommandTypeDao {

    private static final String TAG = "CommandTypeDao";

    private DatabaseManager dbManager;

    public CommandTypeDao(Context context){
        //context.deleteDatabase("robodb");
        dbManager = new DatabaseManager(context);
        Log.d(TAG, "dbManager created successfully");
    }

    //method to get return list of rows based on keyword
    public List<CommandTypeDto> getRows(String keyword){
        CommandTypeDto dto = null;
        List<CommandTypeDto> list = new ArrayList<CommandTypeDto>();

        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();
        Log.d(TAG,"Version Database: "+sqLiteDatabase.getVersion());

        //write query for getting speech based upon keyword
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT keyword, type FROM "+dbManager.TABLE_COMMANDTYPE+" WHERE keyword = ?", new String[]{ keyword });

        try {
            while (cursor.moveToNext()) {
                dto = new CommandTypeDto();
                dto.setType(cursor.getString(cursor.getColumnIndex(dbManager.TYPE)));
                dto.setKeyword(cursor.getString(cursor.getColumnIndex(dbManager.KEYWORDCT)));

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

    //method to get return list of rows based on keyword
    public List<CommandTypeDto> getAllRows(){
        CommandTypeDto dto = null;
        List<CommandTypeDto> list = new ArrayList<CommandTypeDto>();

        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();
        Log.d(TAG,"Version Database: "+sqLiteDatabase.getVersion());

        //write query for getting speech based upon keyword
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT keyword, type FROM "+dbManager.TABLE_COMMANDTYPE,null);

        try {
            while (cursor.moveToNext()) {
                dto = new CommandTypeDto();
                dto.setType(cursor.getString(cursor.getColumnIndex(dbManager.TYPE)));
                dto.setKeyword(cursor.getString(cursor.getColumnIndex(dbManager.KEYWORDCT)));

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
    public void insertRow(CommandTypeDto dto){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbManager.KEYWORDCT,dto.getKeyword());
        values.put(dbManager.TYPE, dto.getType());

        sqLiteDatabase.insert(dbManager.TABLE_COMMANDTYPE, null, values);

        sqLiteDatabase.close();
    }

    //method to remove record from actionspeech table
    public void removeRow(String keyword){
        SQLiteDatabase sqLiteDatabase = dbManager.getWritableDatabase();
        String whereClause = dbManager.KEYWORDCT + " = ?";
        String[] whereArgs = { keyword };
        sqLiteDatabase.delete(dbManager.TABLE_COMMANDTYPE, whereClause, whereArgs);

        sqLiteDatabase.close();
    }
}
