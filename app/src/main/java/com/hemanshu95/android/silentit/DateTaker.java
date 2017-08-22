package com.hemanshu95.android.silentit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;


public class DateTaker extends ActionBarActivity {

    Button ok;
    String pos;
    DatePicker dp;
    Bundle data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_taker);
        dp=(DatePicker)findViewById(R.id.datePicker);
        ok=(Button)findViewById(R.id.bokd);
        Intent intent=getIntent();
        //ok=(Button)findViewById(R.id.bokt);
        if(intent!=null && intent.hasExtra("data"))
        {
            data= intent.getParcelableExtra("data");

        }
        //ok=(Button)findViewById(R.id.bokt);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=dp.getDayOfMonth()+"-"+dp.getMonth()+"-"+dp.getYear();
                if(data.getString("position").equals("3"))
                {
                    data.putString("datestartd",Integer.toString(dp.getDayOfMonth()));
                    data.putString("datestartm",Integer.toString(dp.getMonth()+1));
                    data.putString("datestarty",Integer.toString(dp.getYear()));
                }
                else
                {
                    data.putString("dateendd",Integer.toString(dp.getDayOfMonth()));
                    data.putString("dateendm",Integer.toString(dp.getMonth()+1));
                    data.putString("dateendy",Integer.toString(dp.getYear()));
                }

                Intent intent = new Intent(getApplication(),Input.class)
                        .putExtra("data",data);
                //.putExtra("hours",tp.getCurrentHour())
                //.putExtra("minutes",tp.getCurrentMinute());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date_taker, menu);
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
