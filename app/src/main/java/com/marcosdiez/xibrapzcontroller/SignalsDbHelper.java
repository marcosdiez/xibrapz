package com.marcosdiez.xibrapzcontroller;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Marcos on 12/19/13.
 */
public class SignalsDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "XB-SignalsDbHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "signals.sqlite3";
    public static final String SIGNALS_DATA_TABLE_NAME = "SignalData";
    private static final String SIGNALS_DATA_TABLE_CREATE =

            "CREATE TABLE '"+ SIGNALS_DATA_TABLE_NAME +  "'" +
            "(" +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
            "'event_name' TEXT NOT NULL," +
            "'event_value' TEXT NOT NULL," +
            "'timestamp_event_received' INTEGER NOT NULL default '0'," +
            "'lat' REAL NOT NULL default '0',"+
            "'lng' REAL NOT NULL default '0',"+
            "'sent_to_server' BOOLEAN NOT NULL default 0 CHECK (sent_to_server  IN (0,1) )," +
            "'timestamp_sent_to_server' INTEGER NOT NULL default '0'" +
            ");";


    SignalsDbHelper() {
        super(Globals.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SIGNALS_DATA_TABLE_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,  int oldVersion, int newVersion ) {
        Log.d(TAG, "Upgrading SQLite DB from " + oldVersion + " to " + newVersion);
        int currentVersion = oldVersion;


//        if(currentVersion < 10){
//            currentVersion = 10;
//            Log.d(TAG, "Upgrading SQLite DB to " + 10);
//            sqLiteDatabase.execSQL("ALTER TABLE PortalData ADD COLUMN 'address' TEXT default NULL;");
//        }
//
//
//        Log.d(TAG, "Upgrading SQLite DB to " + DATABASE_VERSION);
//        sqLiteDatabase.execSQL("UPDATE PortalData SET 'address' = null WHERE address = '';");

        Log.d(TAG, "Upgrading SQLite DB completed...");
    }
}
