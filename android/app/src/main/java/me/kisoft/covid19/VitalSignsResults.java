package me.kisoft.covid19;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.kisoft.covid19.models.Vitals;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;

public class VitalSignsResults extends AppCompatActivity {
    private Vitals vitals;
    String date;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    private int VBP1, VBP2, VRR, VHR, VO2;
    private PatientService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_results);
        getSupportActionBar().setTitle(R.string.vital_result_title);
        service = new PatientServiceDelegate();

        date = df.format(today);
        TextView VSRR = findViewById(R.id.RRV);
        TextView VSBPS = findViewById(R.id.BP2V);
        TextView VSHR = findViewById(R.id.HRV);
        TextView VSO2 = findViewById(R.id.O2V);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VRR = bundle.getInt("breath");
            VHR = bundle.getInt("bpm");
            VBP1 = bundle.getInt("SP");
            VBP2 = bundle.getInt("DP");
            VO2 = bundle.getInt("O2R");
            vitals = new Vitals(VRR, VHR, VBP1, VBP2, VO2);
            postVitalsResults(vitals);
            VSRR.setText(String.valueOf(VRR));
            VSHR.setText(String.valueOf(VHR));
            VSBPS.setText(VBP1 + " / " + VBP2);
            VSO2.setText(String.valueOf(VO2));
        }

    }

    private void postVitalsResults(final Vitals vitals) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return service.postVitals(vitals);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Log.i("PostVital", "PostVital is sucessful");
                } else {
                    Log.i("PostVital", "PostVital is not sucessful");
                }
                super.onPostExecute(result);
            }
        }.execute();
    }
}
