package com.marcosdiez.xibrapzcontroller;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "XB-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Globals.setContext(this);

        Log.d(TAG, "INIT");

        GpsStuff.getMyGpsStuff().refreshLocation();

        SignalParser x = new SignalParser();
        x.insertEvent("marcos", "diez");

        x.insertEvent("marcos2", "diez2");

        x.insertEvent("marcos", "diez");

        x.insertEvent("marcos3", "diez3");
        x.insertEvent("marcos", "diez");
        Log.d(TAG, "DONE");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
