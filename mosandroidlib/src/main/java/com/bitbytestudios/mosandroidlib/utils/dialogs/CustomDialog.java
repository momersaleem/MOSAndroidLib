package com.bitbytestudios.mosandroidlib.utils.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


public class CustomDialog {

    AlertDialog.Builder mDialogBuilder;
    AlertDialog mDialog;

    //create an object of SingleObject
    private static CustomDialog instance = new CustomDialog();

    //make the constructor private so that this class cannot be
    //instantiated
    private CustomDialog() {
    }

    //Get the only object available
    public static CustomDialog getInstance() {
        return instance;
    }

    public View createView(Context context, int layout) {

        LayoutInflater layoutInflater
                = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mCustomView = layoutInflater.inflate(layout, null);

        mDialogBuilder = new AlertDialog.Builder(context);
        mDialogBuilder.setView(mCustomView);
        mDialogBuilder.setCancelable(true);
        mDialog = mDialogBuilder.create();
        mDialog.show();

        return mCustomView;
    }

    public void hideView() {
        mDialog.dismiss();
    }
}
