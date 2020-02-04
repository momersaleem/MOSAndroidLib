package com.bitbytestudios.mosandroidlib.pickers;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by SD SOUL on 8/19/2016.
 */
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = TimePickerFragment.class.getSimpleName();

    private OnTimerPickerDialogListener onTimerPickerDialogListener = null;

    public static TimePickerFragment newInstance() {

        Bundle args = new Bundle();

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, false);

//        return new TimePickerDialog(getActivity(), this, hour, minute,
//                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        if (onTimerPickerDialogListener != null)
            onTimerPickerDialogListener.onTimeSelected(view, (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay, minute);

        dismiss();
    }

    public interface OnTimerPickerDialogListener {
        void onTimeSelected(TimePicker view, int hourOfDay, int minute);
    }

    public OnTimerPickerDialogListener getOnTimerPickerDialogListener() {
        return onTimerPickerDialogListener;
    }

    public void setOnTimerPickerDialogListener(OnTimerPickerDialogListener onTimerPickerDialogListener) {
        this.onTimerPickerDialogListener = onTimerPickerDialogListener;
    }
}