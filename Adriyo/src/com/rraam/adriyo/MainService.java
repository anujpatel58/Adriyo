package com.rraam.adriyo;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class MainService extends IntentService {
    private NotificationManager notiManager;

    public MainService() {
        super("MainService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Reminder");
    }

    private void sendNotification(String msg) {
    	notiManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this).setContentTitle("Reminder").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);
        notiBuilder.setContentIntent(contentIntent);
        notiManager.notify(1, notiBuilder.build());
        startActivity(new Intent(MainService.this, PopUpActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}