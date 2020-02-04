package com.bitbytestudios.mosandroidlib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by SD SOUL on 8/19/2016.
 */
public class OtherUtil {

    public static final String TAG = OtherUtil.class.getSimpleName();

    /****
     * Image Aspect ration calculation formula
     * adjusted width = <user-chosen height> * original width / original height
     * adjusted height = <user-chosen width> * original height / original width
     *
     * @param pContext
     * @param pUri
     * @return
     */
    public static String convertUriToBase64(final Context pContext, Uri pUri) {
        String encoded = "";

        try {
            int angle = imageOrientationValidator(pContext, pUri);
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(pContext.getContentResolver(), pUri);
            imageBitmap = rotateImage(imageBitmap, angle);
            encoded = convertBitMapToBase64(pContext, imageBitmap);
            Log.e(TAG, "encoded = " + encoded);
        } catch (Exception ex) {
            Log.e(TAG, "Error occured while converting uri to base64 and message = " + ex.getMessage());
        }

        return encoded;
    }

    public static Bitmap convertUriToSmallBitmap(final Context pContext, Uri pUri) {
        Bitmap imageBitmap = null;
        try {
            int angle = imageOrientationValidator(pContext, pUri);
            imageBitmap = MediaStore.Images.Media.getBitmap(pContext.getContentResolver(), pUri);
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, 200, 200, false);
            imageBitmap = rotateImage(imageBitmap, angle);

        } catch (Exception ex) {
            Log.e(TAG, "Error in converting uri to small bitmap and message = " + ex.getMessage());
        }
        return imageBitmap;
    }

    /***
     * @param pContext
     * @param imageBitmap
     * @return
     */
    public static String convertBitMapToBase64(final Context pContext, Bitmap imageBitmap) {
        String encoded = "";

        try {
            int defaultSize = 1200;
            int originalWidth = imageBitmap.getWidth();
            int originalHegiht = imageBitmap.getHeight();
            int adjustHeight = defaultSize * originalHegiht / originalWidth;
            int adjustWidth = defaultSize * originalWidth / originalHegiht;
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, defaultSize, adjustHeight, false);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        } catch (Exception ex) {
            Log.e(TAG, "Error in converting bitmap to base64 in message = " + ex.getMessage());
        }

        return encoded;
    }

    /**
     * @param pContext
     * @param pUri
     * @return
     */
    private static int imageOrientationValidator(final Context pContext, final Uri pUri) {

        ExifInterface ei;
        int angle = 0;
        try {
            String filePath = getRealPathFromURI(pContext, pUri);
            ei = new ExifInterface(filePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return angle;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {

        try {
            File myFile = new File(contentUri.getPath());
            return myFile.getAbsolutePath();
        } catch (Exception e) {
            Log.e(TAG, "Error getting real path from uri and message = " + e.getMessage());
            return "";
        }
    }

    /***
     * @param source
     * @param angle
     * @return
     */
    private static Bitmap rotateImage(Bitmap source, float angle) {

        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                    matrix, true);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bitmap;
    }
}
