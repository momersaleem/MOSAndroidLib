package com.bitbytestudios.mosandroidlib.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class LogUtil {

    public static final String TAG = LogUtil.class.getSimpleName();

    static final boolean IS_DEVELOPMENT_MODE = true;

    public static void info(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.i(TAG, msg);
        }
    }

    public static void debug(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.d(TAG, msg);
        }
    }

    public static void error(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.e(TAG, msg);
        }
    }

    public static void verbose(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.v(TAG, msg);
        }
    }

    public static void warn(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.w(TAG, msg);
        }
    }

    // ////////////////////////////////////

    /**
     * write the crash logs to a file
     */

    static final String DIR = "Haulwire";
    public static final String FILE = "log.txt";

    public static final String ERROR_FILE = "crash log.txt";

    public static void writeLogToFile(String logText, String filename,
                                      boolean append) {

        if (IS_DEVELOPMENT_MODE) {

            try {
                File root = new File(Environment.getExternalStorageDirectory(),
                        DIR);
                if (!root.exists())
                    root.mkdirs();
                Calendar calender = Calendar.getInstance();
                String error = calender.getTime() + "\n" + logText;
                File file = new File(root, filename);
                FileWriter writer = new FileWriter(file, append);
                writer.write(error);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void writeLogInfo(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE)
            if (msg != null)
                Log.i(tag, msg);
    }

    public static void writeLogError(String tag, String msg) {
        if (IS_DEVELOPMENT_MODE)
            if (msg != null)
                Log.e(tag, msg);
    }
}
