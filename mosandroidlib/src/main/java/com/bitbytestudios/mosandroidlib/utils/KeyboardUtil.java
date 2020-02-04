package com.bitbytestudios.mosandroidlib.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by SD SOUL on 8/19/2016.
 */
public class KeyboardUtil {

    public static final String TAG = KeyboardUtil.class.getSimpleName();

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
