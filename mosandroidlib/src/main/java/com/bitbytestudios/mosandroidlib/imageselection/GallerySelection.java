package com.bitbytestudios.mosandroidlib.imageselection;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by SD SOUL on 6/24/2016.
 */
public class GallerySelection extends ImageSelection {

    private Context mContext;

    public GallerySelection(Context context) {
        mContext = context;
    }

    @Override
    public void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        ((AppCompatActivity) mContext).startActivityForResult(Intent.createChooser(intent, ""), PhotoConstants.REQUEST_IMAGE_GALLERY);
    }
}
