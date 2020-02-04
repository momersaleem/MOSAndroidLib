/**
 *
 */
package com.bitbytestudios.mosandroidlib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 *
 */
public class PreferenceUtil {

    private static final String TAG = PreferenceUtil.class.getSimpleName();
    private final String PrefName = "prefs_app_name";

    private static PreferenceUtil ourInstance = null;

    private static Context mContext = null;
    private SharedPreferences pref = null;

    public static PreferenceUtil getInstance(Context pContext) {
        mContext = pContext;

        if (ourInstance == null) {
            ourInstance = new PreferenceUtil();
        }

        return ourInstance;
    }

    private PreferenceUtil() {
        pref = mContext.getSharedPreferences(PrefName, Context.MODE_PRIVATE);
    }

    public void clearPreference() {
        try {
            pref.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pRef
     * @param value
     */
    public void saveString(final String pRef, final String value) {

        try {
            Editor editor = pref.edit();
            editor.putString(pRef, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    /**
     * @param pRef
     * @param defaultValue
     * @return String
     */
    public String getString(final String pRef, final String defaultValue) {
        return pref.getString(pRef, defaultValue);
    }

    /**
     * @param pRef
     * @param value
     */
    public void saveInteger(final String pRef, final int value) {

        try {
            Editor editor = pref.edit();
            editor.putInt(pRef, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    /**
     * @param pRef
     * @param defaultValue
     * @return String
     */
    public int getInteger(final String pRef, final int defaultValue) {
        return pref.getInt(pRef, defaultValue);
    }

    /**
     * @param pRef
     * @param value
     */
    public void saveBool(final String pRef, final boolean value) {

        try {
            Editor editor = pref.edit();
            editor.putBoolean(pRef, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    /**
     * @param pRef
     * @param defaultValue
     * @return String
     */
    public boolean getBool(final String pRef, final boolean defaultValue) {
        return pref.getBoolean(pRef, defaultValue);
    }

    /**
     * @param pRef
     * @param value
     */
    public void saveFloat(final String pRef, final float value) {

        try {
            Editor editor = pref.edit();
            editor.putFloat(pRef, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    /**
     * @param pRef
     * @param defaultValue
     * @return String
     */
    public float getFloat(final String pRef, final float defaultValue) {
        return pref.getFloat(pRef, defaultValue);
    }

    /**
     * @param pRef
     * @param value
     */
    public void saveLong(final String pRef, final long value) {

        try {
            Editor editor = pref.edit();
            editor.putLong(pRef, value);
            editor.commit();
        } catch (Exception e) {
            Log.e(TAG, "" + e.getMessage());
        }
    }

    /**
     * @param pRef
     * @param defaultValue
     * @return String
     */
    public long getLong(final String pRef, final long defaultValue) {
        return pref.getLong(pRef, defaultValue);
    }
}
