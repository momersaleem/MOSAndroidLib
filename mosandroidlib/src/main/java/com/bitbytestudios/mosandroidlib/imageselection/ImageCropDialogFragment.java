package com.bitbytestudios.mosandroidlib.imageselection;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bitbytestudios.mosandroidlib.R;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;

import java.io.File;

public class ImageCropDialogFragment
        extends DialogFragment
        implements View.OnClickListener {

    public static final String TAG = ImageCropDialogFragment.class.getCanonicalName();

    private OnImageCropListener onImageCropListener = null;

    private CropImageView mCropView = null;
    private TextView tvDone = null;
    private ProgressBar pbLoader = null;

    private Uri sourceUri;

    public static ImageCropDialogFragment newInstance() {

        Bundle args = new Bundle();

        ImageCropDialogFragment fragment = new ImageCropDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Uri getSourceUri() {
        return sourceUri;
    }

    public void setSourceUri(Uri sourceUri) {
        this.sourceUri = sourceUri;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Translucent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_crop, container);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Drawable drawable = new ColorDrawable(Color.rgb(0, 0, 0));
        drawable.setAlpha(210);
        view.setBackground(drawable);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCropView = (CropImageView) view.findViewById(R.id.cropImageView);
        mCropView.setCropMode(CropImageView.CropMode.SQUARE);
        mCropView.startLoad(getSourceUri(), mLoadCallback);

        pbLoader = (ProgressBar) view.findViewById(R.id.pbLoader);

        tvDone = (TextView) view.findViewById(R.id.tvDone);
        tvDone.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tvDone) {
            cropImage();
        }
    }

    // Callbacks ///////////////////////////////////////////////////////////////////////////////////
    private final LoadCallback mLoadCallback = new LoadCallback() {
        @Override
        public void onSuccess() {
            //dismissProgress();
        }

        @Override
        public void onError() {
            //dismissProgress();
        }
    };

    private Bitmap croppedBitmap;

    private final CropCallback mCropCallback = new CropCallback() {
        @Override
        public void onSuccess(Bitmap cropped) {
            croppedBitmap = cropped;
        }

        @Override
        public void onError() {
        }
    };

    private final SaveCallback mSaveCallback = new SaveCallback() {
        @Override
        public void onSuccess(Uri outputUri) {
            pbLoader.setVisibility(View.INVISIBLE);
            if (onImageCropListener != null)
                onImageCropListener.onImageCrop(outputUri, croppedBitmap);

            dismiss();
        }

        @Override
        public void onError() {
            // dismissProgress();
        }
    };

    public interface OnImageCropListener {
        void onImageCrop(Uri outputUri, Bitmap bitmap);
    }

    public OnImageCropListener getOnImageCropListener() {
        return onImageCropListener;
    }

    public void setOnImageCropListener(OnImageCropListener onImageCropListener) {
        this.onImageCropListener = onImageCropListener;
    }

    public void cropImage() {
        pbLoader.setVisibility(View.VISIBLE);
        mCropView.startCrop(createSaveUri(), mCropCallback, mSaveCallback);
    }

    public Uri createSaveUri() {
        return Uri.fromFile(new File(getActivity().getCacheDir(), "cropped.jpg"));
    }
}
