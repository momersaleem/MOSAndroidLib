package com.bitbytestudios.mosandroidlib.imageselection;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by SD SOUL on 6/24/2016.
 */
public class PhotoManager {

    public static final String TAG = PhotoManager.class.getSimpleName();
    Uri path = null;

    /****
     * Image Aspect ration calculation formula
     * adjusted width = <user-chosen height> * original width / original height
     * adjusted height = <user-chosen width> * original height / original width
     *
     * @param context
     * @param uri
     * @return
     */
    public Uri fixOrientation(final Context context, final Uri uri) {

        //new Thread(new Runnable() {
//        @Override
//        public void run () {
        try {
            // get photo orientation
            int angle = getOrientationAngle(context, uri);

            // get image bitmap
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            // change orientation if required
            imageBitmap = rotateImage(imageBitmap, angle);

            // save bitmap image to storage
            path = saveImageToSDCard(imageBitmap);

            //ValidationUtil.showToastMsg(context, path.toString());

        } catch (Exception ex) {
            Log.e(TAG, "Error occured in getURIPath method and message = " + ex.getMessage());
        }
        //}
        // }).start();

        return path;
    }

    /**
     * get orientation angle of image
     *
     * @param context
     * @param uri
     * @return
     */
    private int getOrientationAngle(final Context context, final Uri uri) {

        ExifInterface ei;
        int angle = 0;
        try {
            String filePath = getRealPathFromURI(context, uri);
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

    /**
     * gets absolute path of image either chosen from gallery or captures from camera
     *
     * @param context
     * @param contentUri
     * @return
     */
    public String getRealPathFromURI(Context context, Uri contentUri) {

        // if image is taken from camera else if chosen from gallery
        if (contentUri.toString().contains("Pictures")) {

            try {
                File myFile = new File(contentUri.getPath());
                return myFile.getAbsolutePath();
            } catch (Exception e) {
                Log.e(TAG, "Error getting real path from uri and message = " + e.getMessage());
                return "";
            }

        } else {

            Cursor cursor = null;
            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {

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

    public static Uri saveImageToSDCard(Bitmap bitmap) {

        String fileName = "profile_image.jpg";

        FileOutputStream outStream;
        File pictureFile = null;
        try {

            File direct = new File(Environment.getExternalStorageDirectory() + "/SmartDrive");

            if (!direct.exists()) {
                File newDirect = new File("/sdcard/SmartDrive/");
                newDirect.mkdirs();
            }

            pictureFile = new File(new File("/sdcard/SmartDrive/"), fileName);
            if (pictureFile.exists()) {
                pictureFile.delete();
            }

            if (!pictureFile.exists())
                pictureFile.createNewFile();

            outStream = new FileOutputStream(pictureFile.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            return Uri.fromFile(pictureFile);
            //return pictureFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public void cropImage(FragmentActivity activity, Uri data, ImageCropDialogFragment.OnImageCropListener listener) {

        ImageCropDialogFragment fragment = ImageCropDialogFragment.newInstance();
        fragment.setSourceUri(data);
        fragment.setOnImageCropListener(listener);
        fragment.show(activity.getSupportFragmentManager(), ImageCropDialogFragment.TAG);
    }
}
