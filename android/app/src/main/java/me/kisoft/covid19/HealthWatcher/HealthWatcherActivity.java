package me.kisoft.covid19.HealthWatcher;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import me.kisoft.covid19.R;

public class HealthWatcherActivity extends AppCompatActivity {

    private TextView tvHeartRate;
    private TextView tvRespirationRate;
    private TextView tvBloodPressure;
    private TextView tvO2Saturation;
    private TextView tvVitalSigns;

    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_watcher);
        Log.e("","Primary");
        tvHeartRate = findViewById(R.id.tv_heart_rate);
        tvRespirationRate = findViewById(R.id.tv_respiration_rate);
        tvBloodPressure = findViewById(R.id.tv_blood_pressure);
        tvO2Saturation = findViewById(R.id.tv_o2_saturation);
        tvVitalSigns = findViewById(R.id.tv_vital_signs);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
        tvHeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 1;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                HealthWatcherActivity.this.startActivity(i);
                HealthWatcherActivity.this.finish();
            }
        });

        tvBloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 2;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                HealthWatcherActivity.this.startActivity(i);
                HealthWatcherActivity.this.finish();
            }
        });

        tvRespirationRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 3;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                HealthWatcherActivity.this.startActivity(i);
                HealthWatcherActivity.this.finish();
            }
        });

        tvO2Saturation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 4;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                HealthWatcherActivity.this.startActivity(i);
                HealthWatcherActivity.this.finish();

            }
        });

        tvVitalSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 5;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                HealthWatcherActivity.this.startActivity(i);
                HealthWatcherActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        HealthWatcherActivity.super.onBackPressed();
                        HealthWatcherActivity.this.finish();
                        System.exit(0);
                    }
                }).create().show();
    }


}

