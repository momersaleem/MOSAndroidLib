package com.bitbytestudios.mosandroidlib.utils.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.bitbytestudios.mosandroidlib.R;

public class MyAlertDialogBox {

    AlertDialog.Builder dialog;

    public MyAlertDialogBox(Context context, String title, String mesg) {

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

    public MyAlertDialogBox(Context context, String title, String mesg,
                            String neutralButton, DialogInterface.OnClickListener listner) {

        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(mesg);
        dialog.setNeutralButton(neutralButton, listner);

        dialog.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    public MyAlertDialogBox(Context context, String title, String mesg,
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

    public MyAlertDialogBox(Context context, String title, String mesg,
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
