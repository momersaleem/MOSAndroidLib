package com.bitbytestudios.mosandroidlib.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MySharedPreferences {
    /**
     * data members
     */
    public SharedPreferences instanceSP;

    private static MySharedPreferences instance = null;

    // private Context mContext;

    /**
     * Constructor
     *
     * @param context
     */
    // private Constructor to make it singleton
    private MySharedPreferences(Context context) {
        // this.mContext = context;
        instanceSP = PreferenceManager.getDefaultSharedPreferences(context
                .getApplicationContext());
    }

    /**
     * methods
     */
    // get instance to insure only single instance is exist
    public static MySharedPreferences getInstance(Context context) {

        if (null == instance) {
            instance = new MySharedPreferences(context);
        }

        return instance;
    }

    // read integer preferences
    public int readInt(String key) {
        return instanceSP.getInt(key, -1);
    }

    // read String preferences
    public String readString(String key) {
        return instanceSP.getString(key, "");
    }

    public String readString(String key, String defaultVal) {
        return instanceSP.getString(key, defaultVal);
    }

    // read boolean preferences and default value will be false
    public boolean readBool(String key, boolean defaultVal) {
        return instanceSP.getBoolean(key, defaultVal);
    }

    // save String preferences
    public boolean savePreferences(String key, String value) {
        return instanceSP.edit().putString(key, value).commit();
    }

    // save integer preferences
    public boolean savePreferences(String key, int value) {
        return instanceSP.edit().putInt(key, value).commit();
    }

    // save boolean preferences
    public boolean savePreferences(String key, boolean value) {
        return instanceSP.edit().putBoolean(key, value).commit();
    }

    // save boolean preferences
    public void clear() {
        instanceSP.edit().clear().commit();
    }
}
