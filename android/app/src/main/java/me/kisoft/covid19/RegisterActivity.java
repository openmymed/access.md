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
import me.kisoft.covid19.services.PatientServiceDelegate;
import me.kisoft.covid19.utils.KeyboardUtil;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtPhone;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private EditText edtFirstName;
    private EditText edtLastName;
    private Button btnSignUp;
    private TextView tvGoToSignIn;
    private PatientService service;
    private TextView tvRegisterWarning;

    @Override
    protected void onStart() {
        service = new PatientServiceDelegate();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        KeyboardUtil.showKeyboard(this);
        //init screen components
        edtPhone = findViewById(R.id.edt_phone);
        edtPassword = findViewById(R.id.edt_password);
        edtFirstName = findViewById(R.id.et_first_name);
        edtFirstName.requestFocus();
        edtLastName = findViewById(R.id.et_last_name);
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
                KeyboardUtil.hideKeyboard(RegisterActivity.this);
                tvRegisterWarning.setVisibility(View.GONE);
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String phone = edtPhone.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) && !TextUtils.isEmpty(firstName)&& !TextUtils.isEmpty(lastName)) {
                    if (password.equals(confirmPassword)) {
                        Patient patient = new Patient(phone, password, phone,firstName,lastName);
                        register(patient);
                    }else{
                        tvRegisterWarning.setText(R.string.confirm_pass_warning);
                        tvRegisterWarning.setVisibility(View.VISIBLE);
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
                Boolean result = service.register(patient);
                if (result) {
                    //login internally for the session
                    service.login(patient.getUsername(), patient.getPassword());
                }
                return result;
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
