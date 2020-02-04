package com.bitbytestudios.mosandroidlib.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by SD SOUL on 8/19/2016.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = DatePickerFragment.class.getSimpleName();

    private OnDatePickerDialogListener onDatePickerDialogListener = null;

    public static DatePickerFragment newInstance() {

        Bundle args = new Bundle();

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        if (onDatePickerDialogListener != null)
            onDatePickerDialogListener.onDateSelected(view, year, month, day);
    }

    public interface OnDatePickerDialogListener {
        void onDateSelected(DatePicker view, int year, int month, int day);
    }

    public OnDatePickerDialogListener getOnDatePickerDialogListener() {
        return onDatePickerDialogListener;
    }

    public void setOnDatePickerDialogListener(OnDatePickerDialogListener onDatePickerDialogListener) {
        this.onDatePickerDialogListener = onDatePickerDialogListener;
    }
}
