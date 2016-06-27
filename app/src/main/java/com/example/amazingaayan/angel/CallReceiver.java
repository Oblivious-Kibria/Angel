package com.example.amazingaayan.angel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by Amazing Aayan on 28-Jun-16.
 */
public class CallReceiver extends BroadcastReceiver {
    AudioManager mode = null;
    TelephonyManager telephony;
    SmsManager smsManager;
    @Override
    public void onReceive(final Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        // to get the phone state if the phone is silent;
        mode = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentMode = mode.getRingerMode();
        telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        smsManager = SmsManager.getDefault();

        //to get the sharedpreference;
        SharedPreferences newSharedPrefs = context.getSharedPreferences("AngelState", context.MODE_PRIVATE);

        //for Fajr;
        final Long timePickerTime1 = newSharedPrefs.getLong("TimePicker_Time1", 0);
        final Long timePickerTimeAdded1 = newSharedPrefs.getLong("TimePicker_Time_Added1", 0);
        final Boolean switch1_Status = newSharedPrefs.getBoolean("Switch1", false);

        //for Zuhr;
        final Long timePickerTime2 = newSharedPrefs.getLong("TimePicker_Time2", 0);
        final Long timePickerTimeAdded2 = newSharedPrefs.getLong("TimePicker_Time_Added2", 0);
        final Boolean switch2_Status = newSharedPrefs.getBoolean("Switch2", false);

        //for Asr;
        final Long timePickerTime3 = newSharedPrefs.getLong("TimePicker_Time3", 0);
        final Long timePickerTimeAdded3 = newSharedPrefs.getLong("TimePicker_Time_Added3", 0);
        final Boolean switch3_Status = newSharedPrefs.getBoolean("Switch3", false);

        //for Maghrib;
        final Long timePickerTime4 = newSharedPrefs.getLong("TimePicker_Time4", 0);
        final Long timePickerTimeAdded4 = newSharedPrefs.getLong("TimePicker_Time_Added4", 0);
        final Boolean switch4_Status = newSharedPrefs.getBoolean("Switch4", false);

        //for Isha;
        final Long timePickerTime5 = newSharedPrefs.getLong("TimePicker_Time5", 0);
        final Long timePickerTimeAdded5 = newSharedPrefs.getLong("TimePicker_Time_Added5", 0);
        final Boolean switch5_Status = newSharedPrefs.getBoolean("Switch5", false);

        //for Jumuah;
        final Long timePickerTime6 = newSharedPrefs.getLong("TimePicker_Time6", 0);
        final Long timePickerTimeAdded6 = newSharedPrefs.getLong("TimePicker_Time_Added6", 0);
        final Boolean switch6_Status = newSharedPrefs.getBoolean("Switch6", false);


        // for Fajr;
        if (switch1_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime1 && System.currentTimeMillis() < timePickerTimeAdded1) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime1 + " " + timePickerTimeAdded1, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }

        // for Zuhr;
        if (switch2_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime2 && System.currentTimeMillis() < timePickerTimeAdded2) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime2 + " " + timePickerTimeAdded2, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }

        // for Asr;

        if (switch3_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime3 && System.currentTimeMillis() < timePickerTimeAdded3) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime3 + " " + timePickerTimeAdded3, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }

        // for Maghrib;
        if (switch4_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime4 && System.currentTimeMillis() < timePickerTimeAdded4) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime4 + " " + timePickerTimeAdded4, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }

        // for Isha;

        if (switch5_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime5 && System.currentTimeMillis() < timePickerTimeAdded5) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime5 + " " + timePickerTimeAdded5, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }

        // for Jumuah;

        if (switch6_Status == true && currentMode == AudioManager.RINGER_MODE_SILENT) {
            if (System.currentTimeMillis() >= timePickerTime6 && System.currentTimeMillis() < timePickerTimeAdded6) {
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                    telephony.listen(new PhoneStateListener() {
                        @Override
                        public void onCallStateChanged(int state, String incomingNumber) {
                            super.onCallStateChanged(state, incomingNumber);
                            //Toast.makeText(context, "Number " + incomingNumber + " Time " + timePickerTime6 + " " + timePickerTimeAdded6, Toast.LENGTH_LONG).show();

                            try {
                                Class c = Class.forName(telephony.getClass().getName());
                                Method m = c.getDeclaredMethod("getITelephony");
                                m.setAccessible(true);
                                Object telephonyService = m.invoke(telephony);

                                c = Class.forName(telephonyService.getClass().getName());
                                m = c.getDeclaredMethod("endCall");
                                m.setAccessible(true);
                                m.invoke(telephonyService);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                smsManager.sendTextMessage(incomingNumber, null, "I am in prayer. Please call me later! ", null, null);
                            } catch (Exception ex) {
                                //Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                    }, PhoneStateListener.LISTEN_CALL_STATE);
                }
            }
        }








    }
}

