package com.example.amazingaayan.angel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Amazing Aayan on 20-Jun-16.
 */
public class AlarmReceiver extends BroadcastReceiver{
    AudioManager mode = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences newSharedPrefs = context.getSharedPreferences("AngelState", context.MODE_PRIVATE);
        final Boolean switch1_Status = newSharedPrefs.getBoolean("Switch1", false);
        final Boolean switch2_Status = newSharedPrefs.getBoolean("Switch2", false);
        final Boolean switch3_Status = newSharedPrefs.getBoolean("Switch3", false);
        final Boolean switch4_Status = newSharedPrefs.getBoolean("Switch4", false);
        final Boolean switch5_Status = newSharedPrefs.getBoolean("Switch5", false);
        final Boolean switch6_Status = newSharedPrefs.getBoolean("Switch6", false);

        int INTENT_FLAG = intent.getExtras().getInt("INTENT_FLAG");
        mode = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentMode = mode.getRingerMode();

        if(INTENT_FLAG == 0) {
            if (currentMode == AudioManager.RINGER_MODE_NORMAL) {
                mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        }

        //for Fajr;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch1_Status==true) {
                mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //for Zuhr;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch2_Status==true) {
            mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //for Asr;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch3_Status==true) {
            mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //for Maghrib;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch4_Status==true) {
            mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //for Isha;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch5_Status==true) {
            mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        //for Jumuah;
        if (currentMode == AudioManager.RINGER_MODE_SILENT && switch6_Status==true) {
            mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }
       // Toast.makeText(context, "Alarm is Ringing !", Toast.LENGTH_LONG).show();
    }
}
