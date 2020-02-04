package com.bitbytestudios.mosandroidlib.helpers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.bitbytestudios.mosandroidlib.R;
import com.bitbytestudios.mosandroidlib.utils.debugers.Debuger;
import com.bitbytestudios.mosandroidlib.utils.dialogs.MyAlertDialogBox;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class HelperMethods {

    private static String TAG = "HelperMethods ";

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return true;
            } else {
                Resources res = context.getResources();
                new MyAlertDialogBox(
                        context,
                        res.getString(R.string.helper_network_error),
                        res.getString(R.string.helper_internet_not_available),
                        res.getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });
            }
        } catch (Exception e) {
            Log.e("isNetworkAvailable", "" + e);
        }
        return false;
    }

//    public static void setCheckBoxPadding(Context context, CheckBox checkBox) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        checkBox.setPadding(checkBox.getPaddingLeft()
//                        + (int) (context.getResources().getInteger(R.integer.dp20)
//                        * scale + 0.5f), checkBox.getPaddingTop(),
//                checkBox.getPaddingRight(), checkBox.getPaddingBottom());
//    }

    public static Typeface getTypeFace(Context context, String font) {
        Typeface tpeFace = Typeface.createFromAsset(context.getAssets(),
                "font/" + font);
        return tpeFace;
    }

    public static String encode(String email) {
        try {
            return URLEncoder.encode(email, "UTF-8");
        } catch (Exception e) {
            Debuger.e(TAG, "encode Exception " + e);

        }
        return email.trim();
    }

    @SuppressWarnings("deprecation")
    public static int getdisplayWidth(Activity activity) {
        int width;
        if (android.os.Build.VERSION.SDK_INT < 13) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            width = display.getWidth(); // deprecated
            // height = display.getHeight(); // deprecated
        } else {

            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            // height = size.y;
        }
        return width;
    }

    @SuppressLint("SdCardPath")
    public static void generateNoteOnSD(String sFileName, String sBody) {
        Debuger.i("generateNoteOnSD", "called");
        try {
            File root = new File("/sdcard/WYC-ZONE/Log");
            if (!root.exists()) {
                root.mkdirs();
            }
            // /sdcard/MobilePics Backup/
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile, true);
            Calendar c = Calendar.getInstance();
            writer.append("\n\n\n" + c.getTime() + "\n" + sBody);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertExceptionToString(Exception e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        String s = writer.toString();
        return s;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formateTime(String time) {
        SimpleDateFormat formater = new SimpleDateFormat("h:m:s");

        Date msgDate;
        String newTime = "";
        try {

            msgDate = formater.parse(time);
            formater = new SimpleDateFormat("HH:mm:ss");
            newTime = formater.format(msgDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newTime.length() > 0) {
            return newTime;
        }

        return time;
    }

    public static int convertBooleanToInt(boolean value) {
        if (value)
            return 1;
        return 0;
    }

    public static boolean convertIntToBoolean(int value) {
        if (value == 1)
            return true;
        return false;
    }

    public static boolean convertStringToBool(String favorite) {
        if (favorite.equalsIgnoreCase("Yes") || favorite.equalsIgnoreCase("true"))
            return true;
        return false;
    }

    public static String convertBoolToString(boolean favorite) {
        if (favorite)
            return "true";
        return "false";
    }

    // hides keyboard
    public static void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus()
                    .getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isJSONString(String str) {
        try {
            new JSONObject(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    // checks if a string is empty or null
    public static boolean isEmptyOrNull(String value) {

        if (value.equals(""))
            return true;

        if (value.equals("null"))
            return true;

        if (value.equals("#"))
            return true;

        return false;
    }


    // opens a url in browswer
    public static void openURL(Context context, String url) {
        Intent intent;
        if (!url.startsWith("http")) {
            String httpAddedUrl = "http://" + url;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(httpAddedUrl));
            context.startActivity(intent);
        } else if (URLUtil.isValidUrl(url)) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } else {
            HelperMethods.showToast(context, context.getString(R.string.helper_invalid_url));
        }
    }

    // share business on different platforms
    public static void openShareDialog(Context context, String title, String content) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, content);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, title));
    }
}
