package me.kisoft.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import io.paperdb.Paper;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.utils.Keys;

public class ProfileActivity extends AppCompatActivity {
    private ImageView userImage;
    private TextView tvUserFullName;
    private TextView tvUserAge;

    private TextView tvUserSex;
    private TextView tvUserWeight;
    private TextView tvUserHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        Patient currentPatient = Paper.book().read(Keys.CURRENT_USER_KEY);

        userImage = findViewById(R.id.img_user);
        tvUserFullName = findViewById(R.id.tv_user_full_name);
        tvUserAge = findViewById(R.id.tv_user_age);
        tvUserSex = findViewById(R.id.tv_user_sex);
        tvUserWeight = findViewById(R.id.tv_user_weight);
        tvUserHeight = findViewById(R.id.tv_user_height);
        // placing data in their place..
        String fullName = currentPatient.getFirstName() + " " + currentPatient.getLastName();
        tvUserFullName.setText(fullName);
        tvUserAge.setText(currentPatient.getProfile().getAge() + " " + getString(R.string.years_old));
        tvUserSex.setText(currentPatient.getProfile().getSex().toString());
        tvUserWeight.setText(currentPatient.getProfile().getWeight() + " kg");
        tvUserHeight.setText(currentPatient.getProfile().getHeight() + " cm");
    }
}
