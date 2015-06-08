package com.medbrain.fuzzy.medbrain;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

//import android.content.Intent;

/**
 * Created by Julio on 6/6/15.
 */
public class appNotificationService extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        String appName = intent.getStringExtra("string_value");
        long appTime = intent.getLongExtra("long_value", 1);
        int notificationId = intent.getIntExtra("notification_id", 1);

        Notification n = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_event_available_black_48dp)
                .setContentTitle("Recordatorio de Cita")
                .setContentText(appName)
                .setWhen(appTime)
                .build();

        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);



        nm.notify(notificationId, n);
    }

}
