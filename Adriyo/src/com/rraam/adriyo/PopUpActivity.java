package com.rraam.adriyo;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopUpActivity extends Activity {

    private PendingIntent pendingIntent;
    private Button snooze, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringTone = RingtoneManager.getRingtone(PopUpActivity.this, alarmUri);
        ringTone.play();
        snooze = (Button) findViewById(R.id.snooze);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ringTone.stop();
				finish();
			}
		});
        snooze.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	            Intent myIntent = new Intent(PopUpActivity.this, AlarmReciever.class);
	            pendingIntent = PendingIntent.getBroadcast(PopUpActivity.this, 0002000, myIntent, 0);
            	((AlarmManager) getSystemService(ALARM_SERVICE)).setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + (1000 * 60 * 10), pendingIntent);
            	Toast.makeText(PopUpActivity.this, "Snooze for 10 minutes", Toast.LENGTH_LONG).show();
				ringTone.stop();
            	finish();
			}
		});
    }
}