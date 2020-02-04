package com.bitbytestudios.mosandroidlib.topbars;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bitbytestudios.mosandroidlib.R;

/**
 * Created by Raza on 8/19/2015.
 */
public class TopBar extends LinearLayout {

    private final int mOrientation = LinearLayout.HORIZONTAL;

    ImageView mLeftImage;
    TextView mTextView;
    ImageView mRightImage;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar, 0, 0);
        String titleText = a.getString(R.styleable.TopBar_backgroundColor);

        int valueColor = a.getColor(R.styleable.TopBar_backgroundColor, getResources().getColor(R.color.black));
        a.recycle();

        setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        setOrientation(mOrientation);
        setWeightSum(100);
        setWeightSum(100);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(valueColor);

        mLeftImage = new ImageView(context);
        mTextView = new TextView(context);
        mRightImage = new ImageView(context);

        addView(mLeftImage, 50, 50);
        addView(mTextView);
        addView(mRightImage, 50, 50);

    }

    public void setmLeftImage(int id) {
        mLeftImage.setBackgroundResource(id);
    }

    public void setmTextView(String text) {
        mTextView.setText(text);
    }

    public void setmRightImage(int id) {
        mRightImage.setBackgroundResource(id);
    }

    public void clearViews() {
        mLeftImage.setBackgroundResource(0);
        mTextView.setText("");
        mRightImage.setBackgroundResource(0);
    }
}
