package me.kisoft.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceMock;
import me.kisoft.covid19.utils.RememberMe;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignIn;
    private CheckBox chkRememberMe;
    private TextView tvLoginWarning;
    private TextView tvGoToSignUp;
    private PatientService service;
    //TODO Async coonection with the API,
    //TODO formatting break to different methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Paper.init(this);

        //init screen components
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        chkRememberMe = findViewById(R.id.chk_remember_me);
        tvLoginWarning = findViewById(R.id.tv_login_warning);
        tvGoToSignUp = findViewById(R.id.tv_goto_sign_up);
        //init service
        service = new PatientServiceMock();
        //check if remember me is activated to login immediately
        String phone = Paper.book().read(RememberMe.phone);
        String password = Paper.book().read(RememberMe.password);
        if (service.login(phone, password) != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
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
                    if (service.login(phone, password) != null) {
                        if (chkRememberMe.isChecked()) {
                            Paper.book().write(RememberMe.phone, phone);
                            Paper.book().write(RememberMe.password, password);
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        tvLoginWarning.setText(getString(R.string.sign_in_incorrect));
                        tvLoginWarning.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvLoginWarning.setVisibility(View.VISIBLE);
                }

            }
        });

    }

}
