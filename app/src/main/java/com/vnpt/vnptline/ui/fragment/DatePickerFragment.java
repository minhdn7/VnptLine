package com.vnpt.vnptline.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.vnpt.vnptline.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by MinhDN on 7/3/2018.
 */
@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment {
    private Date mDate;
    private int year, month, day, hour, min;
    private TextView txtTimer;

    @SuppressLint("ValidFragment")
    public DatePickerFragment(TextView txtTimer) {
        this.txtTimer = txtTimer;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);

        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(mDate);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_date_time_picker, null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.date_picker);
//        TimePicker timePicker = (TimePicker)v.findViewById(R.id.time_picker);
//        timePicker.setIs24HourView(true);
//        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                DatePickerFragment.this.year = year;
                DatePickerFragment.this.month = month;
                DatePickerFragment.this.day = day;
                updateDateTime();
            }
        });

//        timePicker.setCurrentHour(hour);
//        timePicker.setCurrentMinute(min);
//        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            public void onTimeChanged(TimePicker view, int hour, int min) {
//                DateTimePickerFragment.this.hour = hour;
//                DateTimePickerFragment.this.min = min;
//                updateDateTime();
//            }
//        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(getString(R.string.chonNgayDat).toUpperCase())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        sendResult(getActivity().RESULT_OK);
                        String time = day + "/" + (month + 1) + "/" + year;
                        txtTimer.setText(time);
                    }
                })
                .create();
    }

    public void updateDateTime() {
        mDate = new GregorianCalendar(year, month, day).getTime();

//        getArguments().putSerializable(EXTRA_DATE, mDate);
    }
}
