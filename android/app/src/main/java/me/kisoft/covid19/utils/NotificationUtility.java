package me.kisoft.covid19.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import me.kisoft.covid19.MainActivity;
import me.kisoft.covid19.R;

import static me.kisoft.covid19.AppWraper.CHANNEL_1_ID;

public class NotificationUtility extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    Context context;
    public NotificationUtility(Context c) {
        this.context=c;
        notificationManager = NotificationManagerCompat.from(c);
    }
    public void notify(Context c, String notificationTitle, String notificationMessage, int notificationRequestCode){

        setNotification(c, notificationTitle,  notificationMessage,  notificationRequestCode);
        newNotification(c,notificationTitle,notificationMessage);
    }

    private void setNotification(Context context, String notificationTitle, String notificationMessage, int notificationRequestCode){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(notificationTitle)
                        .setColor(100)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentText(notificationMessage);

        /*Intent intent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = getActivity(context, notificationRequestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);*/

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    public void newNotification(Context c,String notificationTitle, String notificationMessage ){
        Intent resultIntent = new Intent(this.context,MainActivity.class);
        PendingIntent pendingIntent =    PendingIntent.getActivity(this.context,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(c, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }
}