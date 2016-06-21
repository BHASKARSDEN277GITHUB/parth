package com.kalia.bhaskar.parth.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by bhaskar on 18/6/16.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseManager";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "robodb";

    /*
    * Table details
    * TABLE1: commandtype
    * TABLE2: actionspeech
    * */

    /* TBALE1 */
    public static final String TABLE_COMMANDTYPE= "commandtype";
    public static final String KEYWORDCT= "keyword";
    public static final String TYPE = "type";

    private static final String CREATE_TABLE_COMMANDTYPE=
            "CREATE TABLE " + TABLE_COMMANDTYPE
                    + " (" + KEYWORDCT + " TEXT PRIMARY KEY, "
                    + TYPE + " TEXT NOT NULL);";
    /* //TABLE1*/

    /* TABLE2 */
    public static final String TABLE_ACTIONSPEECH= "actionspeech";
    public static final String KEYWORDAS = "keyword";
    public static final String TEXT = "text";

    private static final String CREATE_TABLE_ACTIONSPEECH =
            "CREATE TABLE " + TABLE_ACTIONSPEECH
                    + " (" + KEYWORDAS + " TEXT PRIMARY KEY, "
                    + TEXT + " TEXT NOT NULL);";

    /* //TABLE2 */

    public DatabaseManager(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMANDTYPE);
        sqLiteDatabase.execSQL(CREATE_TABLE_ACTIONSPEECH);

        //insert predefined data into first table

        sqLiteDatabase.execSQL(createInsertQueryCT("hello", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("what is your name", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("who is your master", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("who built you", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("do you know gora", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("do you know your master", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("what do you eat", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("what is your birthdate", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("what is your national anthem", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("fuck you", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("status", "speak"));
        sqLiteDatabase.execSQL(createInsertQueryCT("how are you", "speak"));


        sqLiteDatabase.execSQL(createInsertQueryCT("sleep", "work"));
        sqLiteDatabase.execSQL(createInsertQueryCT("show commands", "work"));
        sqLiteDatabase.execSQL(createInsertQueryCT("call", "work"));


        //insert predefined data into first table

        sqLiteDatabase.execSQL(createInsertQueryAS("hello", "hello user."));
        sqLiteDatabase.execSQL(createInsertQueryAS("what is your name", "my name is Lisa."));
        sqLiteDatabase.execSQL(createInsertQueryAS("who is your master", "my master is  bhaskar kalia."));
        sqLiteDatabase.execSQL(createInsertQueryAS("who built you", "i was built by bhaskar kalia."));
        sqLiteDatabase.execSQL(createInsertQueryAS("do you know gora", "yes, gora is a good guy, they call him fandi. I love to hang out with him."));
        sqLiteDatabase.execSQL(createInsertQueryAS("do you know your master", "yes, sir my master is a very good person, i always obey him."));
        sqLiteDatabase.execSQL(createInsertQueryAS("what do you eat", "i eat power."));
        sqLiteDatabase.execSQL(createInsertQueryAS("what is your birthdate", "i was born on tuesday forteenth june two thousand and sixteen midnight"));
        sqLiteDatabase.execSQL(createInsertQueryAS("what is your national anthem", "Jana gana mana adhinaayaka jaya hay, Bhaaratha Bhaagya Vidhaata ।\n" +
                "Punjab Sindhu Gujarat Maraatha, Draavida Utkala Banga ।\n" +
                "Vindhya Himaachala Yamuna Ganga, Ucchala jaladhi taranga ।\n" +
                "Tava Shubha naame jaage, Tava shubha aashisha maage ।\n" +
                "Gaahey tava jaya gaathaa ।\n" +
                "Jana Gana Mangala Daayaka jaya hay, Bhaaratha Bhaagya Vidhaata ।\n" +
                "Jaya hay, Jaya hay, Jaya hay ।\n" +
                "Jaya Jaya Jaya Jaya hay ।।"));
        sqLiteDatabase.execSQL(createInsertQueryAS("fuck you", "gaand marwa bhosdy kay"));
        sqLiteDatabase.execSQL(createInsertQueryAS("status", "i can listen, i can speak and i can understand commands. soon i will be able to do things for you."));
        sqLiteDatabase.execSQL(createInsertQueryAS("how are you", "i am fine. thanks for asking."));

        Log.d(TAG,"database manager on create executed successfully");

        //sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    private String createInsertQueryAS(String keyword, String text){
        String query = "insert into "+ TABLE_ACTIONSPEECH + " values ("+"\""+keyword+"\""+","+"\""+text+"\""+")";
        return  query;
    }

    private String createInsertQueryCT(String keyword, String type){
        String query = "insert into "+ TABLE_COMMANDTYPE+ " values ("+"\""+keyword+"\""+","+"\""+type+"\""+")";
        return  query;
    }

}
