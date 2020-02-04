package com.bitbytestudios.mosandroidlib.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 */
public class LoaderUtil {

    public static final String TAG = LoaderUtil.class.getSimpleName();
    
    public static ProgressDialog pDialog;

    public static void showProgressDialog(Context context, String msg) {

        if (pDialog == null)
            pDialog = new ProgressDialog(context);

        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void cancelProgressDialog() {
        if (pDialog != null) {
            pDialog.cancel();
            pDialog = null;
        }
    }
}
