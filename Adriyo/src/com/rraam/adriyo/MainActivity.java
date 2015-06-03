package com.rraam.adriyo;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TimePicker timePicker;
    private TextView intervalText, countText;
    private int count;
    private double interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        intervalText = (TextView) findViewById(R.id.interval);
        countText = (TextView) findViewById(R.id.count);
        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		        interval = Double.parseDouble("0" + intervalText.getText().toString());
		        count = Integer.parseInt("0" + countText.getText().toString());
				Calendar calendar = Calendar.getInstance();
	            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
	            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
	            Intent myIntent = new Intent(MainActivity.this, AlarmReciever.class);
	            ((AlarmManager) getSystemService(ALARM_SERVICE)).setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, 0));
	            long nextAlarm = 0;
	            for(int i=1; i<=count; i++){
	            	nextAlarm += 1000 * 60 * 60 * (long)interval;
		            ((AlarmManager) getSystemService(ALARM_SERVICE)).setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + nextAlarm, PendingIntent.getBroadcast(MainActivity.this, i, myIntent, 0));
	            }
	            Toast.makeText(MainActivity.this, "Reminder set for " + count + " times with " + interval + " hours interval.", Toast.LENGTH_LONG).show();
			}
		});
    }
}