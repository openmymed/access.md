package me.kisoft.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;

public class ChangePasswordActivity extends AppCompatActivity {
    private PatientService service;
    private TextView tvPasswordWarnings;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private Button btnPasswordFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setTitle(R.string.change_password);
        getSupportActionBar().show();
        service = new PatientServiceDelegate();

        tvPasswordWarnings = findViewById(R.id.tv_password_warnings);
        etOldPassword = findViewById(R.id.et_old_password);
        etNewPassword = findViewById(R.id.et_new_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnPasswordFinish = findViewById(R.id.btn_password_finish);

        btnPasswordFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPass = etOldPassword.getText().toString();
                String newPass = etNewPassword.getText().toString();
                String confirmPass = etConfirmPassword.getText().toString();
                tvPasswordWarnings.setVisibility(View.GONE);
                if (!TextUtils.isEmpty(oldPass) && !TextUtils.isEmpty(newPass) && !TextUtils.isEmpty(confirmPass)) {
                    if (newPass.equals(confirmPass)) {
                        changePassword(oldPass, newPass);
                    } else {
                        tvPasswordWarnings.setText(R.string.confirm_pass_warning);
                        tvPasswordWarnings.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvPasswordWarnings.setText(R.string.sign_in_all_fields_required);
                    tvPasswordWarnings.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void changePassword(final String oldPassword, final String newPassword) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return true;
//                return service.changePassword(oldPassword,newPassword);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    tvPasswordWarnings.setText(R.string.password_successfully);
                    tvPasswordWarnings.setTextColor(Color.parseColor("#639a67"));
                    tvPasswordWarnings.setVisibility(View.VISIBLE);
                }
            }
        }.execute();
    }
}
