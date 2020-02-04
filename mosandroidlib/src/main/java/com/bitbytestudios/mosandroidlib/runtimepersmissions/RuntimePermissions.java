package com.bitbytestudios.mosandroidlib.runtimepersmissions;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by SD SOUL on 8/18/2016.
 */
public class RuntimePermissions {

    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final int PERMISSION_REQUEST_CAMERA = 0;

    private static RuntimePermissions ourInstance = new RuntimePermissions();

    private OnRuntimePermissionsListener onRuntimePermissionsListener = null;

    public static RuntimePermissions getInstance() {
        return ourInstance;
    }

    private RuntimePermissions() {
    }

    public boolean hasPermission(FragmentActivity activity, String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    public void requestPermission(FragmentActivity activity, String[] permissions, int permissionRequestCode, OnRuntimePermissionsListener listener) {

        onRuntimePermissionsListener = listener;

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissions[0])) {

            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            //ValidationUtil.showToastMsg(activity, "You can't access camera because you have denied camera permission!");
            ActivityCompat.requestPermissions(activity, permissions, permissionRequestCode);
        } else {

            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity, permissions, permissionRequestCode);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && writeExternalStorageAccepted) {
                        if (onRuntimePermissionsListener != null)
                            onRuntimePermissionsListener.onPermissionGranted();

                    } else {
                        if (onRuntimePermissionsListener != null)
                            onRuntimePermissionsListener.onPermissionDenied();
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
    }

    public interface OnRuntimePermissionsListener {
        void onPermissionGranted();

        void onPermissionDenied();
    }

    public OnRuntimePermissionsListener getOnRuntimePermissionsListener() {
        return onRuntimePermissionsListener;
    }

    public void setOnRuntimePermissionsListener(OnRuntimePermissionsListener onRuntimePermissionsListener) {
        this.onRuntimePermissionsListener = onRuntimePermissionsListener;
    }

}
