package me.kisoft.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceMock;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignIn;
    private CheckBox chkRememberMe;
    private TextView tvLoginWarning;
    private TextView tvGoToSignUp;
    private PatientService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        chkRememberMe = findViewById(R.id.chk_remember_me);
        tvLoginWarning = findViewById(R.id.tv_login_warning);
        tvGoToSignUp = findViewById(R.id.tv_goto_sign_up);
        //init service
        service = new PatientServiceMock();
        //this is used to go the sign up screen
        tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //this goes to the Main screen after checking login credentials
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (true) { //todo complete this button check login credentials
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    tvLoginWarning.setVisibility(View.VISIBLE);
                }

            }
        });

    }
}
