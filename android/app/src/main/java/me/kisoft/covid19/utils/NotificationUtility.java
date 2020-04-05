package me.kisoft.covid19.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import me.kisoft.covid19.MainActivity;
import me.kisoft.covid19.R;

public class NotificationUtility extends AppCompatActivity {
    private final int REQUEST_LOCATION_PERMISSION = 1;

    private static final String CHANNEL_ID ="1" ;
    Context context;
    public NotificationUtility(Context c) {
        this.context=c;

    }
    public void setNotification(Context context, String notificationTitle, String notificationMessage, int notificationRequestCode){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(notificationTitle)
                        .setColor(100)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentText(notificationMessage);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, notificationRequestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


}