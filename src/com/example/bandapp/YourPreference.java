package com.example.bandapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class YourPreference {   
    private static YourPreference yourPreference;
    private SharedPreferences sharedPreferences;

    public static YourPreference getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new YourPreference(context);
        }
        return yourPreference;
    }

    private YourPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("YourCustomNamedPreference",Context.MODE_PRIVATE);
    }

    public void saveData(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.commit();           
    }

    public String getData(String key) {
        if (sharedPreferences!= null) {
           return sharedPreferences.getString(key, "");
        }
        return "";         
    }
}
/*
USAGE:
	YourPreference yourPrefrence = YourPreference.getInstance(context);
	yourPreference.saveData(YOUR_KEY,YOUR_VALUE);
	String value = yourPreference.getData(YOUR_KEY);
*/