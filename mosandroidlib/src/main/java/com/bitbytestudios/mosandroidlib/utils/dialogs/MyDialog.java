package com.bitbytestudios.mosandroidlib.utils.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.bitbytestudios.mosandroidlib.R;

/**
 * Created by Raza on 8/1/2015.
 */
public class MyDialog {

    public class MyAlertDialog {

        AlertDialog.Builder dialog;

        public MyAlertDialog(Context context, String title, String mesg) {

            dialog = new AlertDialog.Builder(context);
            dialog.setTitle(title);
            dialog.setMessage(mesg);
            dialog.setNeutralButton(context.getString(R.string.ok),
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }

        public MyAlertDialog(Context context, String title, String mesg,
                             String neutralButton, DialogInterface.OnClickListener listner) {

            dialog = new AlertDialog.Builder(context);
            dialog.setTitle(title);
            dialog.setMessage(mesg);
            dialog.setNeutralButton(neutralButton, listner);

            dialog.create();
            dialog.setCancelable(false);
            dialog.show();

        }

        public MyAlertDialog(Context context, String title, String mesg,
                             String positiveButton, String negativeButton, String neutralButton,
                             DialogInterface.OnClickListener positiveListner,
                             DialogInterface.OnClickListener negativeListner,
                             DialogInterface.OnClickListener neutralListner) {

            dialog = new AlertDialog.Builder(context);
            dialog.setTitle(title);
            dialog.setMessage(mesg);
            dialog.setPositiveButton(positiveButton, positiveListner);
            dialog.setNeutralButton(neutralButton, neutralListner);
            dialog.setNegativeButton(negativeButton, negativeListner);

            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }

        public MyAlertDialog(Context context, String title, String mesg,
                             String positiveButton, String negativeButton,
                             DialogInterface.OnClickListener positiveListner,
                             DialogInterface.OnClickListener negativeListner) {

            dialog = new AlertDialog.Builder(context);
            dialog.setTitle(title);
            dialog.setMessage(mesg);
            dialog.setPositiveButton(positiveButton, positiveListner);
            dialog.setNegativeButton(negativeButton, negativeListner);

            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    /**
     * CustomDialog class
     */
//    public class MyCustomDialog {
//
//        AlertDialog.Builder mDialogBuilder;
//        AlertDialog mDialog;
//
//        //create an object of SingleObject
//        private static MyCustomDialog instance = new MyCustomDialog();
//
//        //make the constructor private so that this class cannot be
//        //instantiated
//        private MyCustomDialog() {
//        }
//
//        //Get the only object available
//        public static CustomDialog getInstance() {
//            return instance;
//        }
//
//        public View createView(Context context, int layout) {
//
//            LayoutInflater layoutInflater
//                    = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View mCustomView = layoutInflater.inflate(layout, null);
//
//            mDialogBuilder = new AlertDialog.Builder(context);
//            mDialogBuilder.setView(mCustomView);
//            mDialogBuilder.setCancelable(true);
//            mDialog = mDialogBuilder.create();
//            mDialog.show();
//
//            return mCustomView;
//        }
//
//        public void hideView() {
//            mDialog.dismiss();
//        }
//    }
}
