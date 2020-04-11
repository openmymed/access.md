package me.kisoft.covid19.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import io.paperdb.Paper;
import me.kisoft.covid19.R;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Sex;
import me.kisoft.covid19.utils.Keys;

public class GeneralInfoFragment extends Fragment {

    private Button btnNextStep;
    private EditText etAge;
    private EditText etWeight;
    private EditText etHeight;
    private TextView tvRequiredWarning;
    private RadioButton rbFemale;
    private RadioButton rbMale;
    private RadioGroup radioGroup;
    private MedicalProfile profile = null;


    public GeneralInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_info, container, false);
        btnNextStep = view.findViewById(R.id.btn_general_step);
        etWeight = view.findViewById(R.id.et_weight);
        etHeight = view.findViewById(R.id.et_height);
        etAge = view.findViewById(R.id.et_age);
        tvRequiredWarning = view.findViewById(R.id.tv_required_warning);
        rbFemale = view.findViewById(R.id.rb_female);
        rbMale = view.findViewById(R.id.rb_male);
        radioGroup = view.findViewById(R.id.radioGroup);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profile = Paper.book().read(Keys.MED_PROFILE_KEY);
        if (profile != null) {
            etHeight.setText(String.valueOf(profile.getHeight()));
            etAge.setText(String.valueOf(profile.getAge()));
            etWeight.setText(String.valueOf(profile.getWeight()));
            if (profile.getSex() == Sex.Female) {
                rbFemale.setChecked(true);
            } else {
                rbMale.setChecked(true);
            }
        }

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicalProfile newProfile = checkInputs(profile);
                if (newProfile != null) {
                    Paper.book().write(Keys.MED_PROFILE_KEY, newProfile);
                    Navigation.findNavController(view).navigate(R.id.navigation_medication);
                } else {
                    tvRequiredWarning.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private MedicalProfile checkInputs(MedicalProfile profile) {
        if (profile == null) {
            profile = new MedicalProfile();
        }
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();
        String age = etAge.getText().toString();
        Sex sex;
        if (!TextUtils.isEmpty(age) && !TextUtils.isEmpty(height) && !TextUtils.isEmpty(weight) && radioGroup.getCheckedRadioButtonId() != -1) {
            if (rbFemale.isChecked()) {
                sex = Sex.Female;
            } else {
                sex = Sex.Male;
            }
            profile.setAge(Integer.parseInt(age));
            profile.setHeight(Double.parseDouble(height));
            profile.setWeight(Double.parseDouble(weight));
            profile.setSex(sex);
            return profile;
        } else {
            return null;
        }
    }


}
