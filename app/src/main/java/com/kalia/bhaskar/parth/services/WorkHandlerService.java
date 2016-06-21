package com.kalia.bhaskar.parth.services;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.kalia.bhaskar.parth.activities.CommandsListActivity;
import com.kalia.bhaskar.parth.interfaces.DataServiceInterface;
import com.kalia.bhaskar.parth.interfaces.SpeakerServiceInterface;
import com.kalia.bhaskar.parth.interfaces.WorkHandlerServiceInterface;
import com.kalia.bhaskar.parth.robo.InterpretedAction;

import java.util.Arrays;

/**
 * Created by bhaskar on 15/6/16.
 *
 *          there are two types of commands :
 *              commands with arguments
 *              commands without arguments
 *
 *          If you want to create new command/feature which needs arguments,
 *          put an if else clause in work method (to identify command)
 *
 */
public class WorkHandlerService implements WorkHandlerServiceInterface {

    private SpeakerServiceInterface speakerService;
    private DataServiceInterface dataService;

    public WorkHandlerService() {
        speakerService = new SpeakerService();
        dataService = new DataService();
    }

    @Override
    public void work(InterpretedAction ia, Context context, TextToSpeech textToSpeech) {
        try {
            if (ia.getType().equals("speak")) {
                speakerService.speak(dataService.getActionSpeechText(ia.getKeyword(), context), textToSpeech);
            } else {
                String keyword = ia.getKeyword();
                if (keyword.equals("sleep")) {

                } else if (keyword.equals("show commands")) {

                } else if (keyword.startsWith("call")) {
                    Log.d("workhandlerservice: ", keyword);

                    String[] keywordArray = keyword.split(" ");
                    String[] contactNameArray = Arrays.copyOfRange(keywordArray, 1, keywordArray.length);
                    String contactName = TextUtils.join(" ", contactNameArray);

                    makeCall(contactName, context,textToSpeech);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            speakerService.speak("sorry I am little down right now", textToSpeech);
        }
    }

    /* methods for doing work */
    private void sleep(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void showCommands(Context context) {
        Intent i = new Intent(context, CommandsListActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    private void makeCall(String contactName, Context context, TextToSpeech textToSpeech) {
        ContentResolver resolver = context.getContentResolver();
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = resolver.query(uri, projection, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=? COLLATE NOCASE", new String[]{contactName.toLowerCase()}, null);
        String phone = "";
        if (cursor != null) {
            while (cursor.moveToNext()) {
                phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            }
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            callIntent.setData(Uri.parse("tel:" + phone));
            try{
                context.startActivity(callIntent);
            }catch (Exception e){
                e.printStackTrace();
                speakerService.speak("Unable to make call. Please try again.",textToSpeech);
            }
        }
        else{
            speakerService.speak("Unable to find specified contact in contact list ",textToSpeech);
        }
    }
    /* /methods for doing work */
}
