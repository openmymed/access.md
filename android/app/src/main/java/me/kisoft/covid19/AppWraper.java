package me.kisoft.covid19;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;
import me.kisoft.covid19.utils.Keys;
import me.kisoft.covid19.utils.RestClient;

public class AppWraper extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();
        RestClient.init(this.getApplicationContext());
        Paper.init(this.getApplicationContext());
        getSymptoms();
        createNotificationChannels();
    }

    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");
            Log.e("Tag","This should be created");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            Log.e("Tag","This is the desc"+channel1.getDescription());
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

    private void getSymptoms(){
        List<ICPCEntry> icpcEntries = Paper.book().read(Keys.ICPC_KEY);
        if (icpcEntries == null) {
            Log.e("ICPC call", "true");
            new AsyncTask<Void, Boolean, List<ICPCEntry>>() {
                @Override
                protected List<ICPCEntry> doInBackground(Void... voids) {
                    PatientService service = new PatientServiceDelegate();
                    return service.getICPC();
                }

                @Override
                protected void onPostExecute(List<ICPCEntry> icpcEntryList) {
                    super.onPostExecute(icpcEntryList);
                    Paper.book().write(Keys.ICPC_KEY, icpcEntryList);
                }
            }.execute();
        }
    }
}
