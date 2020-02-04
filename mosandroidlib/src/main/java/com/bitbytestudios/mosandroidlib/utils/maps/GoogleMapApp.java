package com.bitbytestudios.mosandroidlib.utils.maps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.bitbytestudios.mosandroidlib.R;
import com.bitbytestudios.mosandroidlib.helpers.HelperMethods;

import java.net.URLEncoder;

/**
 * Created by Raza on 8/1/2015.
 */
public class GoogleMapApp {

    private static String TAG = "GoogleMapApp ";

    // class fields
    final static String TAG_APP_PACKAGE_NAME = "com.google.android.apps.maps";

    // open google map app to show a pin on location specifies using latlngs
    public static void showPinUsingLatlng(Context context, String latitude, String longitude, String locationName) {

        //geo:0,0?q=latitude,longitude(label)
        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "(" + locationName + ")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(TAG_APP_PACKAGE_NAME);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            showToast(context);
        }
    }

    // open google map app to show a pin on location specifies using address
    public static void showPinUsingAddress(Context context, String address) {

        //geo:0,0?q=latitude,longitude(label)
        Uri gmmIntentUri = Uri.parse(String.format("geo:0,0?q=%s", URLEncoder.encode(address)));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(TAG_APP_PACKAGE_NAME);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            showToast(context);
        }
    }

    // checks and opens google map application to show route from current location
    public static void showDirection(Context context, String latitude, String longitude) {

        Uri gmmIntentUri = Uri.parse("google.navigation:q="
                + latitude + "," + longitude);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage(TAG_APP_PACKAGE_NAME);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            showToast(context);
        }
    }

    private static void showToast(Context context) {
        HelperMethods.showToast(context, context.getString(R.string.maps_app_not_installed));
    }
}
