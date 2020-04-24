package me.kisoft.covid19.HealthWatcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import me.kisoft.covid19.R;

public class StartVitalSigns extends AppCompatActivity {
    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vital_signs);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            p = extras.getInt("Page");
        }

        ImageButton VS = this.findViewById(R.id.StartVS);

        VS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch is to decide which activity must be opened
                switch (p) {

                    case 1: {
                        Intent i = new Intent(v.getContext(), HeartRateProcess.class);
                        i.putExtra("Usr", user);
                        StartVitalSigns.this.startActivity(i);
                        StartVitalSigns.this.finish();
                    }
                    break;

                    case 2: {
                        Intent i = new Intent(v.getContext(), BloodPressureProcess.class);
                        i.putExtra("Usr", user);
                        StartVitalSigns.this.startActivity(i);
                        StartVitalSigns.this.finish();
                    }
                    break;

                    case 3: {
                        Intent i = new Intent(v.getContext(), RespirationProcess.class);
                        i.putExtra("Usr", user);
                        StartVitalSigns.this.startActivity(i);
                        StartVitalSigns.this.finish();
                    }
                    break;

                    case 4: {
                        Intent i = new Intent(v.getContext(), O2Process.class);
                        i.putExtra("Usr", user);
                        StartVitalSigns.this.startActivity(i);
                        StartVitalSigns.this.finish();
                    }
                    break;

                    case 5: {
                        Intent i = new Intent(v.getContext(), VitalSignsProcess.class);
                        i.putExtra("Usr", user);
                        StartVitalSigns.this.startActivity(i);
                        StartVitalSigns.this.finish();
                    }
                    break;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(StartVitalSigns.this, HealthWatcherActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }


}
