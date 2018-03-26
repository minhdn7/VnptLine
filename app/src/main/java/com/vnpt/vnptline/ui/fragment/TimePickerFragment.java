package com.vnpt.vnptline.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vnpt.vnptline.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by MinhDN on 26/3/2018.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private int hour, min;
    private TextView txtTimer;

    @SuppressLint("ValidFragment")
    public TimePickerFragment(TextView txtTimer) {
        this.txtTimer = txtTimer;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
//
//        Calendar calendar = Calendar.getInstance();
//        hour = calendar.get(Calendar.HOUR_OF_DAY);
//        min = calendar.get(Calendar.MINUTE);
//
//        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_time_picker, null);
//
//        TimePicker timePicker = (TimePicker)v.findViewById(R.id.time_picker);
//        timePicker.setIs24HourView(true);
//        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
//
//        timePicker.setCurrentHour(hour);
//        timePicker.setCurrentMinute(min);
//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            public void onTimeChanged(TimePicker view, int hour, int min) {
//                TimePickerFragment.this.hour = hour;
//                TimePickerFragment.this.min = min;
//                updateDateTime();
//            }
//        });
//
//        return new AlertDialog.Builder(getActivity())
//                .setView(v)
//                .setTitle(getString(R.string.chonGioDat).toUpperCase())
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                        sendResult(getActivity().RESULT_OK);
//                        String time = hour + ":" + min;
//                        txtTimer.setText(time);
//                    }
//                })
//                .create();
//    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                AlertDialog.THEME_HOLO_DARK,this,hour, min, true);
        timePickerDialog.setTitle(getString(R.string.chonGioDat).toUpperCase());
        return timePickerDialog;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hour = i;
        min = i1;
        String time = String.valueOf(hour) + ":" + String.valueOf(min);
        txtTimer.setText(time);
    }

}
