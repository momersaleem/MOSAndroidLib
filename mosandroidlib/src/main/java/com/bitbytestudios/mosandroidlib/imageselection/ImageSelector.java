package com.bitbytestudios.mosandroidlib.imageselection;

import android.content.Context;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by SD SOUL on 6/24/2016.
 */
public class ImageSelector {
    private static ImageSelector ourInstance = new ImageSelector();

    ImageSelection imageSelection = null;

    public static ImageSelector getInstance() {
        return ourInstance;
    }

    private ImageSelector() {
    }

    public void openSelector(final Context context) {

        final ImagePickerDialogFragment fragment = new ImagePickerDialogFragment();
        fragment.setOnImageSelectionView(new ImagePickerDialogFragment.OnImageSelectionView() {
            @Override
            public void onGalleryImageView() {
                imageSelection = new GallerySelection(context);
                imageSelection.selectImage();
            }

            @Override
            public void onCameraImageView() {
                imageSelection = new CameraSelection(context);
                imageSelection.selectImage();
            }
        });

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                fragment.show(((AppCompatActivity) context).getSupportFragmentManager(), ImagePickerDialogFragment.TAG);
            }
        });


    }
}
