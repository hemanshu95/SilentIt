package com.hemanshu95.android.silentit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DigitalClock;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Input extends ActionBarActivity {
    TextView clstart,clend;
    TextView dtstart,dtend;
    ToggleButton vibr;
    String x,pos;
    Bundle data;
    Time start,end;
    Button ok;
    private AlarmManagerBroadcastReceiver alarm;
    final public static String ONE_TIME = "onetime";
    SeekBar seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        clstart=(TextView)findViewById(R.id.tvcstart);
        clend=(TextView)findViewById(R.id.tvcend);
        dtstart=(TextView)findViewById(R.id.tvstartdate);
        dtend=(TextView)findViewById(R.id.tvenddate);
        clstart.setClickable(true);
        clend.setClickable(true);
        dtstart.setClickable(true);
        dtend.setClickable(true);
        seek=(SeekBar)findViewById(R.id.sbvolume);
        seek.setMax(7);
        vibr=(ToggleButton)findViewById(R.id.tbvibrate);
        ok=(Button)findViewById(R.id.bokf);
        final myDatabaseClass data_taking=new myDatabaseClass(this);
        Intent intent=getIntent();

        //Bundle pos = new Bundle();

        if(intent!=null && intent.hasExtra("data"))
        {
            data= intent.getParcelableExtra("data");


        }
        //SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        pos=data.getString("position");
        seek.setProgress(data.getInt("volume"));
        vibr.setChecked(data.getBoolean("vibrate"));
        Toast.makeText(this,pos, Toast.LENGTH_SHORT).show();
        //if(pos!=null)
        //{
            //if()
            x=null;
            if(data.getString("timestarth").length()<2)
                x="0"+data.getString("timestarth")+":";
            if (x==null)
                x=data.getString("timestarth")+":";
            if(data.getString("timestartm").length()<2)
                x=x+"0";
            x=x+data.getString("timestartm");
            clstart.setText(x);

        x=null;
        if(data.getString("timeendh").length()<2)
            x="0"+data.getString("timeendh")+":";
        if (x==null)
            x=data.getString("timeendh")+":";
        if(data.getString("timeendm").length()<2)
            x=x+"0";
        x=x+data.getString("timeendm");
        clend.setText(x);

        x=null;
        if(data.getString("dateendd").length()<2)
            x="0"+data.getString("dateendd")+"-";
        if (x==null)
            x=data.getString("dateendd")+"-";
        if(data.getString("dateendm").length()<2)
            x=x+"0";
        x=x+data.getString("dateendm")+"-"+data.getString("dateendy");
        dtend.setText(x);

        x=null;
        if(data.getString("datestartd").length()<2)
            x="0"+data.getString("datestartd")+"-";
        if(x==null)
            x=data.getString("datestartd")+"-";
        if(data.getString("datestartm").length()<2)
            x=x+"0";
        x=x+data.getString("datestartm")+"-"+data.getString("datestarty");
        dtstart.setText(x);

        //clend.setText(data.getString(x));    //clend.setText(data.getString("timeend"));
            //dtstart.setText(data.getString("datestart"));
            //
            Toast.makeText(this,"hi"+pos+"hi"+Integer.toString("hello".length()), Toast.LENGTH_SHORT).show();
            /*if(pos.equals("1"))
            {
                Toast.makeText(this,"hello", Toast.LENGTH_SHORT).show();

                //x=intent.getStringExtra("time");
                //Toast.makeText(this,x, Toast.LENGTH_SHORT).show();
                clstart.setText(data.getString("timestart"));
            }
            else if(pos.equals("2"))
            {
                //x=intent.getStringExtra("time");
                clend.setText(data.getString("timeend"));
            }
            else if(pos.equals("3"))
            {
                //x=intent.getStringExtra("date");
                dtstart.setText(data.getString("datestart"));
            }
            else if(pos.equals("4"))
            {
                //x=intent.getStringExtra("date");
                dtend.setText(data.getString("datestart"));
            }*/




        //}
        //else
        //{
          //  Toast.makeText(this,"Null", Toast.LENGTH_SHORT).show();
        //}
        //Toast.makeText(this,"OnResume", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,"OnCreate", Toast.LENGTH_SHORT).show();
        vibr.setClickable(true);
        vibr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true)
                    seek.setProgress(0);
            }
        });
/*
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seek) {
                // TODO Auto-generated method stub
                if (seek != null) {
                    if (seek.getProgress() > 0) {
                        vibr.setChecked(false);
                    }
                }
            }
        });
 */       clstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    data.putString("position","1");
                    data.putBoolean("vibrate", vibr.isChecked());
                    data.putInt("volume",seek.getProgress());
                    Intent intent = new Intent(getApplication(), TimeTaker.class)
                            .putExtra("data",data);
                    startActivity(intent);

            }
        });
        clend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putString("position","2");
                data.putBoolean("vibrate",vibr.isChecked());
                data.putInt("volume",seek.getProgress());
                Intent intent=new Intent(getApplication(),TimeTaker.class)
                        .putExtra("data",data);
                startActivity(intent);

            }
        });
        dtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putString("position","3");
                data.putBoolean("vibrate",vibr.isChecked());
                data.putInt("volume",seek.getProgress());
                Intent intent=new Intent(getApplication(),DateTaker.class)
                        .putExtra("data",data);
                startActivity(intent);
            }
        });
        dtend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.putString("position","4");
                data.putBoolean("vibrate",vibr.isChecked());
                data.putInt("volume",seek.getProgress());
                Intent intent=new Intent(getApplication(),DateTaker.class)
                        .putExtra("data", data);
                startActivity(intent);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), Integer.toString(Integer.parseInt(data.getString("timestarth"))) , Toast.LENGTH_SHORT).show();
                int x=Integer.parseInt(data.getString("timestarth"));
                //start.hour = Integer.parseInt(data.getString("timestarth"));
                /*try {
                    start.hour = Integer.parseInt(data.getString("timestarth"));
                    start.minute = Integer.parseInt(data.getString("timestartm"));
                    start.monthDay = Integer.parseInt(data.getString("datestartd"));
                    start.month = Integer.parseInt(data.getString("datestartm"));
                    start.year = Integer.parseInt(data.getString("datestarty"));
                    end.hour = Integer.parseInt(data.getString("timestarth"));
                    end.minute = Integer.parseInt(data.getString("timestartm"));
                    end.monthDay = Integer.parseInt(data.getString("dateendd"));
                    end.month = Integer.parseInt(data.getString("dateendm"));
                    end.year = Integer.parseInt(data.getString("dateendy"));
                }
                catch(Exception e)
                {
                    Log.e("hello","erroe",e);
                //if(Time.compare(start,end)>0);
                }
                    //Toast.makeText(this,"Start Time should be before End Time", Toast.LENGTH_SHORT).show();
                try {
                  */int flag=1;
                if(Integer.parseInt(data.getString("datestarty"))<Integer.parseInt(data.getString("dateendy")))
                    flag=0;
                else if(Integer.parseInt(data.getString("datestarty"))==Integer.parseInt(data.getString("dateendy")))
                {
                    if(Integer.parseInt(data.getString("datestartm"))<Integer.parseInt(data.getString("dateendm")))
                        flag=0;
                    else if(Integer.parseInt(data.getString("datestartm"))==Integer.parseInt(data.getString("dateendm")))
                    {
                        if(Integer.parseInt(data.getString("datestartd"))<Integer.parseInt(data.getString("dateendd")))
                            flag=0;
                        else if(Integer.parseInt(data.getString("datestartd"))==Integer.parseInt(data.getString("dateendd")))
                        {
                            if(Integer.parseInt(data.getString("timestarth"))<Integer.parseInt(data.getString("timeendh")))
                                flag=0;
                            else if(Integer.parseInt(data.getString("timestarth"))==Integer.parseInt(data.getString("timeendh")))
                            {
                                if(Integer.parseInt(data.getString("timestartm"))<Integer.parseInt(data.getString("timeendm")))
                                    flag=0;

                            }
                        }
                    }

                }
                 if(flag==1)
                 {
                     Toast.makeText(getApplicationContext(),"Start Time Should be before End Time", Toast.LENGTH_SHORT).show();
                 }
                else
                 {
                     data_taking.open();

                     int vibra = 0;
                     if (vibr.isChecked())
                         vibra = 1;
                     data_taking.createEntry(Integer.parseInt(data.getString("timestarth")), Integer.parseInt(data.getString("timestartm")), Integer.parseInt(data.getString("datestartd")), Integer.parseInt(data.getString("datestartm")), Integer.parseInt(data.getString("datestarty")), Integer.parseInt(data.getString("timeendh")), Integer.parseInt(data.getString("timeendm")), Integer.parseInt(data.getString("dateendd")), Integer.parseInt(data.getString("dateendm")), Integer.parseInt(data.getString("dateendy")), vibra, seek.getProgress());

                     Bundle b=new Bundle();
                     b= data_taking.next_time();

                     alarm = new AlarmManagerBroadcastReceiver();
                     if(b!=null)
                         onetimeTimer( b.getInt("year"), b.getInt("month"), b.getInt("day"), b.getInt("hour"), b.getInt("minute"), b.getInt("volume"), b.getBoolean("vibrate"),b.getInt("id"),b.getInt("flag")) ;


                     //Toast.makeText(getApplicationContext(), "YOUR SETTING ARE CHANGED " + Integer.toString(vibra), Toast.LENGTH_LONG).show();
                     data_taking.close();


                     //onetimeTimer(Integer.parseInt(data.getString("datestarty")), Integer.parseInt(data.getString("datestartm")), Integer.parseInt(data.getString("datestartd")), Integer.parseInt(data.getString("timestarth")), Integer.parseInt(data.getString("timestartm")), seek.getProgress(), vibr.isChecked());
                     //onetimeTimer(Integer.parseInt(data.getString("dateendy")), Integer.parseInt(data.getString("dateendm")), Integer.parseInt(data.getString("dateendd")), Integer.parseInt(data.getString("timeendh")), Integer.parseInt(data.getString("timeendm")), 7, true);
                     Intent intent = new Intent(getApplication(), MainActivity.class);
                     startActivity(intent);
                 }
                /*}
                catch (Exception e)
                {
                    Log.e("hiewkjlkef","erererer",e);
                }*/

            }
        });
    }
    public void onetimeTimer(int year,int month,int monthday,int hour,int minute,int volume,boolean vibrate,int id,int flag){
        Context context = this.getApplicationContext();
        if(alarm != null){
            alarm.setOnetimeTimer(context,year,month,monthday,hour,minute,volume,vibrate,id,flag);
        }else{
            Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
