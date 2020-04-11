package me.kisoft.covid19;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.models.Symptom;
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

        Log.e("TAG","Im in App Class on Create");
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        Log.e("TAG","Im in App create Chanels");

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
        List<Symptom> symptoms = Paper.book().read(Keys.SYMPTOMS_KEY);
        if( symptoms == null){
            new AsyncTask<Void,Boolean, List<Symptom>>(){
                @Override
                protected List<Symptom> doInBackground(Void... voids) {
                    PatientService service = new PatientServiceDelegate();
                    return service.getSymptoms();
                }

                @Override
                protected void onPostExecute(List<Symptom> symptoms) {
                    super.onPostExecute(symptoms);
                    Paper.book().write(Keys.SYMPTOMS_KEY,symptoms);
                }
            }.execute();
        }
    }
}
