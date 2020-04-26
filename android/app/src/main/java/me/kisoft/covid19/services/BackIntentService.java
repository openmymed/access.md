package me.kisoft.covid19.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import me.kisoft.covid19.AppWraper;
import me.kisoft.covid19.R;

public class BackIntentService extends IntentService {
private PowerManager.WakeLock wakeLock;
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
        Log.d(TAG," onCreate BackIntentService");
        PowerManager powerManager =  (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"App:WakeLock");
        wakeLock.acquire();
        Log.d(TAG, "Wakelook is aquired");
      if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
          Notification notification = new Notification.Builder(this, AppWraper.CHANNEL_1_ID)
                  .setContentTitle("Example Title")
                  .setContentText("Staying Connected for your health")
                  .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                  .build();
          startForeground(1,notification);
      }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG,"onHandleIntent");
        for (int i = 0; i < 10; i++) {

            Log.d(TAG,"Work  happening "+i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
        Log.d(TAG,"Wake lock released");
    }
}
