package com.bitbytestudios.mosandroidlib.utils.loaders;

import android.app.ProgressDialog;
import android.content.Context;

public class Loaders {

    public static ProgressDialog pDialog;

    public static void showProgressDialog(Context context, String msg) {

        cancelProgressDialog();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void cancelProgressDialog() {
        if (pDialog != null) {
            pDialog.cancel();
        }
    }
}
