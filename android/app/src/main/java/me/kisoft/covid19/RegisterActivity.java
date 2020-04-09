package me.kisoft.covid19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceImpl;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtPhone;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnSignUp;
    private TextView tvGoToSignIn;
    private PatientService service;
    private TextView tvRegisterWarning;

    @Override
    protected void onStart() {
        service = new PatientServiceImpl();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        //init screen components
        edtPhone = findViewById(R.id.edt_phone);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
        tvGoToSignIn = findViewById(R.id.tv_goto_sign_in);
        tvRegisterWarning = findViewById(R.id.tv_register_warning);

        //go back to sign in screen
        tvGoToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edtPhone.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)) {
                    if (password.equals(confirmPassword)) {
                        Patient patient = new Patient(phone, password, phone);
                        register(patient);
                    }
                } else {
                    tvRegisterWarning.setText(R.string.sign_in_all_fields_required);
                    tvRegisterWarning.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void register(final Patient patient) {
        new AsyncTask<Void, Void, Boolean>() {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(RegisterActivity.this);
                dialog.setMessage("Signing up...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setCancelable(false);
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected Boolean doInBackground(Void... voids) {
                return service.register(patient);
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    dialog.dismiss();
                    Intent intent = new Intent(RegisterActivity.this, CreateProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    dialog.dismiss();
                    tvRegisterWarning.setText(R.string.error_sign_up);
                    tvRegisterWarning.setVisibility(View.VISIBLE);
                }
                super.onPostExecute(result);
            }
        }.execute();
    }
}
