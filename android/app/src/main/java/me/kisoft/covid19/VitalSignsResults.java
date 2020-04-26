package me.kisoft.covid19;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_signs_results);
        getSupportActionBar().show();
        getSupportActionBar().setTitle(R.string.vital_result_title);
        service = new PatientServiceDelegate();

        date = df.format(today);
        //init screen contents
        TextView tvVSRR = findViewById(R.id.tv_VSRR);
        TextView tvVSBPS = findViewById(R.id.tv_VSBP);
        TextView tvVSHR = findViewById(R.id.tv_VSHR);
        TextView tvVSO2 = findViewById(R.id.tv_VSO2);
        btnFinish = findViewById(R.id.btn_finish);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VRR = bundle.getInt("breath");
            VHR = bundle.getInt("bpm");
            VBP1 = bundle.getInt("SP");
            VBP2 = bundle.getInt("DP");
            VO2 = bundle.getInt("O2R");
            vitals = new Vitals(VRR, VHR, VBP1, VBP2, VO2);
            postVitalsResults(vitals);
            tvVSRR.setText(String.valueOf(VRR));
            tvVSHR.setText(String.valueOf(VHR));
            tvVSBPS.setText(VBP1 + " / " + VBP2);
            tvVSO2.setText(String.valueOf(VO2));
        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VitalSignsResults.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
