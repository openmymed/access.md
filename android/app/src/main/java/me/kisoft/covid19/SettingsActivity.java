package me.kisoft.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {
    private Button btnChangePassword;
    private Button btnChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(R.string.title_settings);
        btnChangeLanguage = findViewById(R.id.btn_change_language);
        btnChangePassword = findViewById(R.id.btn_change_password);

        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo change language..
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
