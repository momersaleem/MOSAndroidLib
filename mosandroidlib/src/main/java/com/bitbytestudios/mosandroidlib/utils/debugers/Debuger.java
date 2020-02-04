package com.bitbytestudios.mosandroidlib.utils.debugers;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Debuger {

    static final boolean IS_DEVELOPMENT_MODE = true;
    static final String TAG_APP = "WYC-ZONE";

    public static void i(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.i(TAG_APP, TAG + "\n" + msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.d(TAG_APP, TAG + "\n" + msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.e(TAG_APP, TAG + "\n" + msg);
        }
    }

    public static void v(String TAG, String msg) {
        if (IS_DEVELOPMENT_MODE) {
            Log.v(TAG_APP, TAG + "\n" + msg);
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
