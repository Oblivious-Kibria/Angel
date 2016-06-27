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
public class Asr extends Fragment implements HorizontalNumberPickerListener {

    AlarmManager alarmManager;
    TimePicker timePicker;
    Switch aSwitch;
    PendingIntent pending_intent_ON, pending_intent_OFF;
    static int hour, minute, intent_FLAG;
    static Calendar calendar, timePicker_Time, timePicker_Time_Added;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    View view;
    HorizontalNumberPicker horizontalNumberPicker3;
    static int silent_Duration3;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.asr_layout, container, false);

        sharedPrefs = getContext().getSharedPreferences("AngelState", getContext().MODE_PRIVATE); // name of sharedpreference is AngelState;

        timePicker = (TimePicker) view.findViewById(R.id.timePicker3);
        aSwitch = (Switch) view.findViewById(R.id.switch3);

        horizontalNumberPicker3 = (HorizontalNumberPicker) view.findViewById(R.id.horizontal_number_picker3);
        horizontalNumberPicker3.getButtonMinusView().setText("<");
        horizontalNumberPicker3.getButtonPlusView().setText(">");
        horizontalNumberPicker3.setShowLeadingZeros(false);

        // to get the value of number picker;
        sharedPrefs = getContext().getSharedPreferences("AngelState", getContext().MODE_PRIVATE);
        int numberPickerValue = sharedPrefs.getInt("NumberPicker3_Value",5);

        horizontalNumberPicker3.setValue(numberPickerValue);
        horizontalNumberPicker3.setMinValue(5);
        horizontalNumberPicker3.setMaxValue(30);
        horizontalNumberPicker3.getTextValueView().setTextColor(Color.parseColor("#FFFFFF"));
        horizontalNumberPicker3.getButtonMinusView().setTextColor(Color.parseColor("#FFFFFF"));
        horizontalNumberPicker3.getButtonPlusView().setTextColor(Color.parseColor("#FFFFFF"));

        horizontalNumberPicker3.setListener(this);
        
        aSwitch.setChecked(sharedPrefs.getBoolean("Switch3", false));

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
                    editor.putBoolean("Switch3", true);
                    editor.apply();

                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();
                    }

                    silent_Duration3 = horizontalNumberPicker3.getValue();
                    Toast.makeText(getContext(), "Alarm is ON!", Toast.LENGTH_SHORT).show();

                    timePicker_Time.set(Calendar.HOUR_OF_DAY, hour);
                    timePicker_Time.set(Calendar.MINUTE, minute);
                    timePicker_Time.set(Calendar.SECOND, 0);
                    timePicker_Time.set(Calendar.MILLISECOND, 0);

                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putLong("TimePicker_Time3", timePicker_Time.getTimeInMillis());
                    editor.apply();

                    // To check if timePickerTime is greater than the current time;
                    if(timePicker_Time.getTimeInMillis() > calendar.getTimeInMillis()){
                        myIntent1.putExtra("INTENT_FLAG", intent_FLAG=0);
                        pending_intent_ON = PendingIntent.getBroadcast(getContext(), 4, myIntent1, pending_intent_ON.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, timePicker_Time.getTimeInMillis(), pending_intent_ON);
                    }

                    timePicker_Time_Added.set(Calendar.HOUR_OF_DAY, hour);
                    timePicker_Time_Added.set(Calendar.MINUTE, minute+silent_Duration3);
                    timePicker_Time_Added.set(Calendar.SECOND, 0);
                    timePicker_Time_Added.set(Calendar.MILLISECOND, 0);

                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putLong("TimePicker_Time_Added3", timePicker_Time_Added.getTimeInMillis());
                    editor.apply();

                    if(timePicker_Time_Added.getTimeInMillis() > calendar.getTimeInMillis()) {
                        myIntent2.putExtra("INTENT_FLAG", intent_FLAG = 1);
                        pending_intent_OFF = PendingIntent.getBroadcast(getContext(), 5, myIntent2, pending_intent_OFF.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, timePicker_Time_Added.getTimeInMillis(), pending_intent_OFF);
                    }
                }
                else{
                    editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("Switch3", false);
                    editor.apply();
                }
            }
        });

        return  view;
    }

    public static Asr newInstance(){
        Asr asr = new Asr();
        return asr;
    }

    @Override
    public void onHorizontalNumberPickerChanged(HorizontalNumberPicker horizontalNumberPicker, int value) {
        if(horizontalNumberPicker.getId()== R.id.horizontal_number_picker3) {
            // to set the value in SharedPreference;
            int val = horizontalNumberPicker3.getValue();
            editor = getContext().getSharedPreferences("AngelState", Context.MODE_PRIVATE).edit();
            editor.putInt("NumberPicker3_Value", val);
            editor.apply();
        }
    }

}
