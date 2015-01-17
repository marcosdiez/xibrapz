package com.marcosdiez.xibrapzcontroller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Marcos on 17-Jan-15.
 */
public class SignalParser {
    private static final String TAG = "XB-SignalParser";
    public void parse(String msg){

    }
    public void insertEvent(String theEventName, String eventValue) {
        long unixTime = System.currentTimeMillis() / 1000L;
        double lat = GpsStuff.getMyGpsStuff().lat;
        double lng = GpsStuff.getMyGpsStuff().lng;

        ContentValues values = new ContentValues();
        values.put("event_name", theEventName);
        values.put("event_value", eventValue);
        values.put("timestamp_event_received", unixTime);
        values.put("lat", lat);
        values.put("lng", lng);

        Log.d(TAG, "event_name=[" + theEventName + "] event_value=[" +eventValue + "] lat=[" + lat + "] lng=[" + lng + "]" );
        saveEvent(values);
    }

    private void saveEvent(ContentValues values) {
        Log.d(TAG,"saveEvent");
        SignalsDbHelper mSignalsDbHelper = new SignalsDbHelper();
        SQLiteDatabase db = mSignalsDbHelper.getWritableDatabase();
        db.insert(SignalsDbHelper.SIGNALS_DATA_TABLE_NAME,
                null,
                values
                );
        db.close();
    }

}
