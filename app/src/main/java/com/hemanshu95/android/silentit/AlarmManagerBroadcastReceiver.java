package com.hemanshu95.android.silentit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hemanshu_sondhi on 4/14/2015.
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    final public static String ONE_TIME = "onetime";
    AudioManager audioManager;
   // Boolean vibra1;
   // Integer volu;

    public void onetimeTimer( AlarmManagerBroadcastReceiver alarm,Context context,int year,int month,int monthday,int hour,int minute,int volume,boolean vibrate,int id,int fla){
        // Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context,year,month,monthday,hour,minute,volume,vibrate,id,fla);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
        final myDatabaseClass data_taking=new myDatabaseClass(context);
        AlarmManagerBroadcastReceiver alarm;
        data_taking.open();

        //Acquire the lock
        wl.acquire();


        //You can do the processing here.
        Bundle extras = intent.getParcelableExtra("data");
        Boolean vibra1=intent.getBooleanExtra("vibrate",false);
        int volu=intent.getIntExtra("volume",7);
        //StringBuilder msgStr = new StringBuilder();

        //if(extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){
            //Make sure this intent has been sent by the one-time timer button.
          //  msgStr.append("One time Timer : ");
        //}
        //Format formatter = new SimpleDateFormat("hh:mm:ss a");
        //msgStr.append(formatter.format(new Date()));

        String k="false";
        if(extras.getBoolean("vibrate")==true)
            k="true";
        //Toast.makeText(context, "YOUR SETTING ARE CHANGED "+ k, Toast.LENGTH_LONG).show();
        Toast.makeText(context, " heelo " + Boolean.toString(vibra1)+Integer.toString(volu), Toast.LENGTH_LONG).show();
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        if(vibra1==true)
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        if(vibra1==false )
        {
            if(volu==0)
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            else
            {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM ,volu,AudioManager.FLAG_PLAY_SOUND);
            }

        }
         //   audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //elseintent.getIntExtra("id",1)
        //else
          //  audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM ,extras.getInt("volume"),AudioManager.FLAG_PLAY_SOUND);

        //Release the lock
        Bundle b=new Bundle();
        b= data_taking.next_time();
        data_taking.updatedata(intent.getIntExtra("id",1),intent.getIntExtra("flag",5));
        data_taking.deletedata();
        data_taking.close();

        alarm = new AlarmManagerBroadcastReceiver();
        if(b!=null)
        onetimeTimer(alarm,context, b.getInt("year"), b.getInt("month"), b.getInt("day"), b.getInt("hour"), b.getInt("minute"), b.getInt("volume"), b.getBoolean("vibrate"),b.getInt("id"),b.getInt("flag")) ;
        wl.release();

    }

    public void SetAlarm(Context context)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        //After after 5 seconds
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 5 , pi);
    }

    public void CancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    public void setOnetimeTimer(Context context,int year,int month,int monthday,int hour,int minute,int volume,boolean vibrate,int id,int fla){
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        Bundle data=new Bundle();
        data.putInt("volume",volume);
        data.putBoolean("vibrate", vibrate);
        intent.putExtra("data", data);
        intent.putExtra("vibrate",vibrate);
        intent.putExtra("volume",volume);
        intent.putExtra("id",id);
        intent.putExtra("flag",fla);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, monthday,hour, minute, 0);
        long startTime = calendar.getTimeInMillis();
        //volu=volume;
        //vibra1=vibrate;
        //Toast.makeText(context, Long.toString(startTime)+"+"+Long.toString(System.currentTimeMillis()), Toast.LENGTH_LONG).show();

        am.set(AlarmManager.RTC_WAKEUP,  startTime, pi);
        Toast.makeText(context, "hello +"+DateFormat.format("dd/MM/yyyy hh:mm:ss", startTime).toString(), Toast.LENGTH_LONG).show();

    }
}