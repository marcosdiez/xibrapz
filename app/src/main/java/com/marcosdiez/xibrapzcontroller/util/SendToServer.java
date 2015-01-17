package com.marcosdiez.xibrapzcontroller.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.marcosdiez.xibrapzcontroller.Globals;
import com.marcosdiez.xibrapzcontroller.Settings;
import com.marcosdiez.xibrapzcontroller.db.DatabaseManager;
import com.marcosdiez.xibrapzcontroller.db.SignalsDbHelper;

/**
 * Created by Marcos on 17-Jan-15.
 */
public class SendToServer {
    private static final String TAG = "XB-SendToServer";

    public synchronized void publishData() {
        if(!Globals.there_is_data_to_be_sent){
            Log.d(TAG, "There is no data to be sent... bailing.");
            return;
        }
        if (Globals.offline) {
            Log.d(TAG, "We are offline. There is no reason to try to in publish data.");
            return;
        }
        Log.d(TAG, "Starting to publish data to Server.");
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        Cursor queryCursor = queryForNonPublishedItems(db);
        int numRows = queryCursor.getCount();
        Log.d(TAG, "There are " + numRows + " rows to send to server");
        sendCursorDataToServer(db, queryCursor, numRows);
        queryCursor.close();
        DatabaseManager.getInstance().closeDatabase();
        Log.d(TAG, "End of publishData()");
    }

    private void sendCursorDataToServer(SQLiteDatabase db, Cursor queryCursor, int numRows) {
        queryCursor.moveToFirst();
        while (queryCursor.isAfterLast() == false) {
            if (Globals.offline) {
                Log.d(TAG, "Aborting sending " + numRows + "rows to the server.");
                return;
            }
            publish(queryCursor);
            updateDb(db, queryCursor);
            queryCursor.moveToNext();
        }
        Log.d(TAG, "The " + numRows + " were sucessfully sent to the server.");
    }

    private void publish(Cursor queryCursor) {
        String output = generateServerUrl(queryCursor);
        Log.d(TAG, "ServerURL: [" + output + "]");
    }

    private void updateDb(SQLiteDatabase db, Cursor queryCursor) {
        int row_id = queryCursor.getInt(0);

        ContentValues values = new ContentValues();
        values.put(SignalsDbHelper.SIGNALS_ROW_SENT_TO_SERVER, true);

        // updating row
        String where = SignalsDbHelper.SIGNALS_ROW_ID + "=?";
        String[] whereValues = new String[]{String.valueOf(row_id)};

        db.update(SignalsDbHelper.SIGNALS_DATA_TABLE_NAME, values, where, whereValues);
    }

    private String generateServerUrl(Cursor queryCursor) {
        StringBuilder output = new StringBuilder(500);

        output.append(Settings.server_header);
        output.append("?id=" + queryCursor.getInt(0));
        output.append("&event_name=" + queryCursor.getString(1));
        output.append("&event_value=" + queryCursor.getString(2));
        output.append("&timestamp_event=" + queryCursor.getInt(3));
        output.append("&lat=" + queryCursor.getDouble(4));
        output.append("&lng=" + queryCursor.getDouble(5));
        String outputUrl = output.toString();
        return outputUrl;
    }

    private Cursor queryForNonPublishedItems(SQLiteDatabase db) {
        String[] db_FROM = {"id", "event_name", "event_value", "timestamp_event_received", "lat", "lng"};

        String where = "sent_to_server=?";
        String[] whereArgs = new String[]{"0"};

        String sortOrder = "id ASC";

        Cursor queryCursor = db.query(SignalsDbHelper.SIGNALS_DATA_TABLE_NAME, db_FROM, where, whereArgs, null, null, sortOrder);
        return queryCursor;
    }
}
