package me.kisoft.covid19.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.kisoft.covid19.AppWraper;
import me.kisoft.covid19.MainActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.utils.NotificationUtility;

public class BackIntentService extends IntentService {
private PowerManager.WakeLock wakeLock;
    PatientService service;
    Context context;
public static final String TAG="BackIntentService";
    public BackIntentService() {
        super("BackgroundIntenet service ");
        //restarts if killed
        //Service will be started again
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        service = new PatientServiceDelegate();
        context=this.getApplicationContext();

        Log.d(TAG," onCreate BackIntentService");
        PowerManager powerManager =  (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"App:WakeLock");
        wakeLock.acquire();
        Log.d(TAG, "Wakelook is aquired");
      if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
          Notification notification = new Notification.Builder(this, AppWraper.CHANNEL_2_ID)
                  .setContentTitle("Connected")
                  .setContentText("Staying Connected for your health")
                  .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                  .build();
          startForeground(3,notification);
      }

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"onHandleIntent");
        for (int i = 0; i < 100; i++) {
            getNotifications();
            Log.d(TAG,"Work  happening "+i);
            SystemClock.sleep(5000);
        }
        //we should delete this once the background process is ready.
 /*      Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getNotifications();
                Log.d(TAG,"Work  happening ");

            }
        };
        timer.schedule(task, 0l, 1000 * 5 );*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
        Log.d(TAG,"Wake lock released");
    }
    //todo we need to move this method into the background process...
    private void getNotifications() {
        Log.e("GETNOTIFICATIONS","get notificiation is called");
        new AsyncTask<Void, Void, List<me.kisoft.covid19.models.Notification>>() {
            @Override
            protected List<me.kisoft.covid19.models.Notification> doInBackground(Void... voids) {
                return service.getNotification();
            }

            @Override
            protected void onPostExecute(List<me.kisoft.covid19.models.Notification> notifications) {
                super.onPostExecute(notifications);
                if (notifications != null && !notifications.isEmpty()) {
                    NotificationUtility notificationUtil = new NotificationUtility(context);
                    notificationUtil.notify(context, "Access.md Notification", getString(R.string.notification_question, notifications.size()), 1);
                }
            }
        }.execute();
    }
}
