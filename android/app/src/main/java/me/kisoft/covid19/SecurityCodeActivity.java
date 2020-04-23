package me.kisoft.covid19;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.kisoft.covid19.models.SecurityCode;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;

public class SecurityCodeActivity extends AppCompatActivity {
    private TextView tvSecurityCode;
    private PatientService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_code);
        getSupportActionBar().hide();

        tvSecurityCode = findViewById(R.id.tv_security_code);

        service = new PatientServiceDelegate();
        getSecurityCode();
    }

    private void getSecurityCode() {
        new AsyncTask<Void, Void, SecurityCode>() {

            @Override
            protected SecurityCode doInBackground(Void... voids) {
                return service.getSecurityCode();
            }

            @Override
            protected void onPostExecute(SecurityCode s) {
                tvSecurityCode.setText(s.getCode());
                super.onPostExecute(s);
            }
        }.execute();
    }
}
