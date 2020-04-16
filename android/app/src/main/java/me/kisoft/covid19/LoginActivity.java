package me.kisoft.covid19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.UserRole;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;
import me.kisoft.covid19.utils.Keys;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignIn;
    private CheckBox chkRememberMe;
    private TextView tvLoginWarning;
    private TextView tvGoToSignUp;
    private PatientService service;

    @Override
    protected void onStart() {
        service = new PatientServiceDelegate();
        //check if remember me is activated to login immediately
        String phone = Paper.book().read(Keys.PHONE_KEY);
        String password = Paper.book().read(Keys.PASSWORD_KEY);
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password))
            login(phone, password);

        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //init screen components
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        chkRememberMe = findViewById(R.id.chk_remember_me);
        tvLoginWarning = findViewById(R.id.tv_login_warning);
        tvGoToSignUp = findViewById(R.id.tv_goto_sign_up);

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
                String phone = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
                    login(phone, password);
                } else {
                    tvLoginWarning.setText(R.string.sign_in_all_fields_required);
                    tvLoginWarning.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void login(final String username, final String password) {
        new AsyncTask<Void, Void, Patient>() {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setMessage("Logging In...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setCancelable(false);
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected Patient doInBackground(Void... voids) {
                return service.login(username, password);
            }

            @Override
            protected void onPostExecute(Patient p) {
                if (p != null) {
                    if (p.getUserRole() == UserRole.ROLE_PATIENT) {
                        tvLoginWarning.setVisibility(View.GONE);
                        if (chkRememberMe.isChecked()) {
                            Paper.book().write(Keys.PHONE_KEY, username);
                            Paper.book().write(Keys.PASSWORD_KEY, password);
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        tvLoginWarning.setText(getString(R.string.sigin_auth_account));
                        tvLoginWarning.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvLoginWarning.setText(getString(R.string.sign_in_incorrect));
                    tvLoginWarning.setVisibility(View.VISIBLE);
                }
                dialog.dismiss();
                super.onPostExecute(p);
            }
        }.execute();
    }
}
