package com.hemanshu95.android.silentit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hemanshu_sondhi on 4/10/2015.
 */
public class ListButton extends Fragment {
    public ListButton() {

    }

    Button add;
//    SeekBar seek;
    //TextView tvseek1;
    AudioManager audioManager;
    ArrayAdapter<String> scheduleAdapter;

    //FrameLayout frame1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        add = (Button) rootView.findViewById(R.id.badd);
        //seek = (SeekBar) rootView.findViewById(R.id.sbseek);
        //tvseek1=((TextView)rootView.findViewById(R.id.tvseek));
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //frame1=(FrameLayout)rootView.findViewById(R.id.framelayout2);
        scheduleAdapter= new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                new ArrayList<String>());//weekForecast);
        ListView listView=(ListView)rootView.findViewById(R.id.lvschedule);
        listView.setAdapter(scheduleAdapter);
        String[] result;


        scheduleAdapter.clear();
        final myDatabaseClass data_taking=new myDatabaseClass(getActivity());
        data_taking.open();

        Cursor cursor=data_taking.getAllData();
        String x;
        if (cursor!=null && cursor.moveToFirst()) {
            do {
                String m="am";
                if (cursor.getInt(1)>12)
                {
                    m="pm";
                    if(cursor.getInt(1)<22)
                        x="0"+Integer.toString(cursor.getInt(1)-12);
                    else
                        x = Integer.toString(cursor.getInt(1)-12);

                }
                else
                {
                    if(cursor.getInt(1)<10 &&cursor.getInt(1)>0)
                        x="0"+Integer.toString(cursor.getInt(1));
                    else if(cursor.getInt(1)==0)
                        x="12";
                    else
                    x = Integer.toString(cursor.getInt(1));
                }
                x=x+" : ";
                if(cursor.getInt(2)<10)
                    x=x+"0";
                x =x+ Integer.toString(cursor.getInt(2))+" "+m+" , ";
                if(cursor.getInt(3)<10)
                    x=x+"0";
                x=x+cursor.getInt(3)+" - ";
                if(cursor.getInt(4)<10)
                    x=x+"0";
                x=x+cursor.getInt(4)+" - "+cursor.getInt(5);

                scheduleAdapter.add(x);

            } while (cursor.moveToNext());
        } data_taking.close();
        //data_taking.createEntry(Integer.parseInt(data.getString("timestarth")), Integer.parseInt(data.getString("timestartm")), Integer.parseInt(data.getString("datestartd")), Integer.parseInt(data.getString("datestartm")),Integer.parseInt(data.getString("datestarty")),Integer.parseInt(data.getString("timeendh")), Integer.parseInt(data.getString("timeendm")), Integer.parseInt(data.getString("dateendd")), Integer.parseInt(data.getString("dateendm")),Integer.parseInt(data.getString("dateendy")), vibra, seek.getProgress());



        //final AudioManager audioManager=new AudioManager();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                //
                // audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
               // audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM,7,AudioManager.FLAG_VIBRATE);
                //audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                //audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
                //frame1.setVisibility(View.VISIBLE);
                Bundle bundle=new Bundle();
                Time currentTime = new Time();
                currentTime.setToNow();
                bundle.putString("position","0");
                bundle.putString("timestarth",Integer.toString(currentTime.hour));
                bundle.putString("timestartm",Integer.toString(currentTime.minute));
                bundle.putString("timeendh",Integer.toString(currentTime.hour));
                bundle.putString("timeendm",Integer.toString(currentTime.minute));
                //bundle.putString("timeend",Integer.toString(currentTime.hour)+":"+Integer.toString(currentTime.minute));
                bundle.putString("datestartd",Integer.toString(currentTime.monthDay));//"14-11-1995");
                bundle.putString("datestartm",Integer.toString(currentTime.month+1));
                bundle.putString("datestarty",Integer.toString(currentTime.year));
                bundle.putString("dateendd",Integer.toString(currentTime.monthDay));//"14-11-1995");
                bundle.putString("dateendm",Integer.toString(currentTime.month+1));
                bundle.putString("dateendy",Integer.toString(currentTime.year));
                bundle.putInt("volume",7);
                bundle.putBoolean("vibrate",false);
                //bundle.putString("dateend","04-12-1995");
                Intent intent=new Intent(getActivity(),Input.class)
                        .putExtra("data",bundle);
                startActivity(intent);

                //audioManager.setVolumeControlStream(AudioManager.STREAM_MUSIC)
                    /*try {
                        Log.d("silent it","hello");

                    }
                    catch (Exception e){
                        Log.e("Silent It", "Error ", e);
                    */

                // }
                //audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);

            }
        });




        return rootView;
    }


}
/*seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seek, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                if(seek!=null) {
                    int seekValue = seek.getProgress();
                    String x = "Value: " + Integer.toString(seekValue);
                    tvseek1.setText(x);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seek) {
                // TODO Auto-generated method stub
            }

            /*@Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }*/
            /*@Override
            public void onStopTrackingTouch(SeekBar seek) {
                // TODO Auto-generated method stub
                if(seek!=null) {
                    int seekValue = seek.getProgress();


                    int x1=audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                    int x2=audioManager.getStreamVolume(AudioManager.STREAM_RING);
                    int x3=audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                    String x = "Value: " + Integer.toString(seekValue);
                       tvseek1.setText(x+' '+Integer.toString(x1)+' '+Integer.toString(x2)+ ' '+Integer.toString(x3));

                }
            }




        });*/