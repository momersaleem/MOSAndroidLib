package com.bitbytestudios.mosandroidlib.imageselection;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bitbytestudios.mosandroidlib.R;

public class ImagePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = ImagePickerDialogFragment.class.getCanonicalName();

    private OnImageSelectionView onImageSelectionView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Translucent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_selection_option, container);
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

        TextView cameraTextView = view.findViewById(R.id.cameraTextView);
        TextView galleryTextView = view.findViewById(R.id.galleryTextView);

        cameraTextView.setOnClickListener(this);
        galleryTextView.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.cameraTextView) {
            if (getOnImageSelectionView() != null)
                getOnImageSelectionView().onCameraImageView();
            dismiss();
        } else if (v.getId() == R.id.galleryTextView) {
            if (getOnImageSelectionView() != null)
                getOnImageSelectionView().onGalleryImageView();
            dismiss();
        }
    }

    public interface OnImageSelectionView {
        void onGalleryImageView();

        void onCameraImageView();
    }

    public OnImageSelectionView getOnImageSelectionView() {
        return onImageSelectionView;
    }

    public void setOnImageSelectionView(OnImageSelectionView onImageSelectionView) {
        this.onImageSelectionView = onImageSelectionView;
    }
}
