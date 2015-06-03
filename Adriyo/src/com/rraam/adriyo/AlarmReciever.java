package com.rraam.adriyo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReciever extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Intent intentService = new Intent(context, MainService.class);
        startWakefulService(context, intentService);
        setResultCode(Activity.RESULT_OK);
    }
}