package com.example.amazingaayan.angel;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import com.hrules.horizontalnumberpicker.HorizontalNumberPicker;
import com.hrules.horizontalnumberpicker.HorizontalNumberPickerListener;
import java.util.Calendar;

/**
 * Created by Amazing Aayan on 24-Jun-16.
 */
public class Jumuah extends Fragment implements HorizontalNumberPickerListener {

    AlarmManager alarmManager;
    TimePicker timePicker;
    Switch aSwitch;
    PendingIntent pending_intent_ON, pending_intent_OFF;
    static int hour, minute, intent_FLAG;
    static Calendar calendar, timePicker_Time, timePicker_Time_Added;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    View view;
    HorizontalNumberPicker horizontalNumberPicker6;
    static int silent_Duration6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.jumuah_layout, container, false);

        sharedPrefs = getContext().getSharedPreferences("AngelState", getContext().MODE_PRIVATE); // name of sharedpreference is AngelState;

        timePicker = (TimePicker) view.findViewById(R.id.timePicker6);
        aSwitch = (Switch) view.findViewById(R.id.switch6);

        horizontalNumberPicker6 = (HorizontalNumberPicker) view.findViewById(R.id.horizontal_number_picker6);
        horizontalNumberPicker6.getButtonMinusView().setText("<");
        horizontalNumberPicker6.getButtonPlusView().setText(">");
        horizontalNumberPicker6.setShowLeadingZeros(false);

        // to get the value of number picker;
        sharedPrefs = getContext().getSharedPreferences("AngelState", getContext().MODE_PRIVATE);
        int numberPickerValue = sharedPrefs.getInt("NumberPicker6_Value",5);

        horizontalNumberPicker6.setValue(numberPickerValue);
        horizontalNumberPicker6.setMinValue(5);
        horizontalNumberPicker6.setMaxValue(30);
        horizontalNumberPicker6.getTextValueView().setTextColor(Color.parseColor("#FFFFFF"));
        horizontalNumberPicker6.getButtonMinusView().setTextColor(Color.parseColor("#FFFFFF"));
        horizontalNumberPicker6.getButtonPlusView().setTextColor(Color.parseColor("#FFFFFF"));

        horizontalNumberPicker6.setListener(this);

        aSwitch.setChecked(sharedPrefs.getBoolean("Switch6", false));

        final Intent myIntent1 = new Intent(getContext(), AlarmReceiver.class);
        final Intent myIntent2 = new Intent(getContext(), AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);

        calendar = Calendar.getInstance();
        timePicker_Time = Calendar.getInstance();
        timePicker_Time_Added = Calendar.getInstance();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("Switch6", true);
                    editor.apply();

                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();
                    }

                    silent_Duration6 = horizontalNumberPicker6.getValue();
                    Toast.makeText(getContext(), "Alarm is ON!", Toast.LENGTH_SHORT).show();

                    timePicker_Time.set(Calendar.HOUR_OF_DAY, hour);
                    timePicker_Time.set(Calendar.MINUTE, minute);
                    timePicker_Time.set(Calendar.SECOND, 0);
                    timePicker_Time.set(Calendar.MILLISECOND, 0);

                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putLong("TimePicker_Time6", timePicker_Time.getTimeInMillis());
                    editor.apply();

                    // To check if timePickerTime is greater than the current time;
                    if(timePicker_Time.getTimeInMillis() > calendar.getTimeInMillis()){
                        myIntent1.putExtra("INTENT_FLAG", intent_FLAG=0);
                        pending_intent_ON = PendingIntent.getBroadcast(getContext(), 10, myIntent1, pending_intent_ON.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, timePicker_Time.getTimeInMillis(), pending_intent_ON);
                    }

                    timePicker_Time_Added.set(Calendar.HOUR_OF_DAY, hour);
                    timePicker_Time_Added.set(Calendar.MINUTE, minute+silent_Duration6);
                    timePicker_Time_Added.set(Calendar.SECOND, 0);
                    timePicker_Time_Added.set(Calendar.MILLISECOND, 0);

                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putLong("TimePicker_Time_Added6", timePicker_Time_Added.getTimeInMillis());
                    editor.apply();

                    if(timePicker_Time_Added.getTimeInMillis() > calendar.getTimeInMillis()) {
                        myIntent2.putExtra("INTENT_FLAG", intent_FLAG = 1);
                        pending_intent_OFF = PendingIntent.getBroadcast(getContext(), 11, myIntent2, pending_intent_OFF.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, timePicker_Time_Added.getTimeInMillis(), pending_intent_OFF);
                    }
                }
                else{
                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("Switch6", false);
                    editor.apply();
                }
            }
        });

        return  view;
    }

    public static Jumuah newInstance(){
        Jumuah jumuah = new Jumuah();
        return jumuah;
    }


    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {
        if(horizontalNumberPicker.getId()== R.id.horizontal_number_picker6) {
            int val = horizontalNumberPicker6.getValue();
            editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
            editor.putInt("NumberPicker6_Value", val);
            editor.apply();
        }
    }

}
