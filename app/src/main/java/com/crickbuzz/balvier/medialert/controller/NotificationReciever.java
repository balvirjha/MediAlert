package com.crickbuzz.balvier.medialert.controller;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.crickbuzz.balvier.medialert.R;
import com.crickbuzz.balvier.medialert.modal.MedicinePOJO;
import com.crickbuzz.balvier.medialert.view.MainActivity;

/**
 * Created by Balvier on 9/16/2017.
 */

public class NotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        MedicinePOJO medicinePOJO = (MedicinePOJO) intent.getSerializableExtra("medicinepojo");

        if (medicinePOJO != null && medicinePOJO.isNotificationEnabled()) {
            Log.e("bvc", "NotificationReciever called ");
            Intent notificationIntent = new Intent(context.getApplicationContext(), MainActivity.class);
            notificationIntent.putExtra("medicinepojo", medicinePOJO);
            notificationIntent.setAction("Cancel");

            PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

            NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(context.getApplicationContext(), null)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setAutoCancel(true)
                    .setContentTitle("Take medicine")
                    .setContentText("You must take " + medicinePOJO.getMedicineName() + " " + medicinePOJO.getDosage() + " times")
                    .setLights(Color.BLUE, 500, 500)
                    .setStyle(new NotificationCompat.InboxStyle())
                    .setContentIntent(contentIntent)
                    .setSound(alarmSound);

            NotificationManager manager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1, notificationCompat.build());
        }

    }

}
