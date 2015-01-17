package com.marcosdiez.xibrapzcontroller;

import android.content.Context;
import android.os.Environment;

//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationServices;

/**
 * Created by Marcos Diez on 2015-01-17.
 */
public class Globals {
    private static Context context=null;

    public static void setContext(Context context){
        Globals.context=context;
    }
    public static Context getContext(){
        if(context == null){
            throw new NullPointerException("Context");
        }
        return context;
    }
    public static boolean isContextNull(){
        return context==null;
    }


// private static GoogleApiClient googleApiClient = null;
//    public synchronized static GoogleApiClient getGoogleApiClient(){
//        if(googleApiClient == null){
//            googleApiClient = new GoogleApiClient.Builder(getContext())
//                    .addApi(LocationServices.API)
//                    .build();
//            googleApiClient.disconnect();
//
//        }
//        return googleApiClient;
//    }


    public static String getPublicWritableFolder(){
        return Environment.getExternalStorageDirectory() + "/Android/data/" +
                Globals.getContext().getPackageName();
    }
}
