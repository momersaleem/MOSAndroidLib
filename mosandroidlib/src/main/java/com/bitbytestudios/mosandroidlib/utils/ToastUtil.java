package com.bitbytestudios.mosandroidlib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by SD SOUL on 8/19/2016.
 */
public class ToastUtil {

    public static final String TAG = ToastUtil.class.getSimpleName();

    public static void showToastMsg(final Context pContext, final String pMsg) {
        Toast.makeText(pContext, pMsg, Toast.LENGTH_SHORT).show();
    }
}
