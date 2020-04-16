package me.kisoft.covid19.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.MainActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;
import me.kisoft.covid19.utils.Keys;

public class MedicalFlagsFragment extends Fragment {
    private Button btnFinish;
    private Button btnPrev;

    private CheckBox chkG6PD;
    private CheckBox chkObesity;
    private CheckBox chkDiabetes;
    private CheckBox chkRespiratory;
    private CheckBox chkCardiovascular;
    private MedicalProfile profile;
    private PatientService service;

    public MedicalFlagsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_flags, container, false);
        btnFinish = view.findViewById(R.id.btn_flags_next);
        btnPrev = view.findViewById(R.id.btn_flags_prev);
        chkG6PD = view.findViewById(R.id.chk_g6pd);
        chkRespiratory = view.findViewById(R.id.chk_respiratory);
        chkCardiovascular = view.findViewById(R.id.chk_cardiovascular);
        chkDiabetes = view.findViewById(R.id.chk_diabetes);
        chkObesity = view.findViewById(R.id.chk_obesity);
        service = new PatientServiceDelegate();

        profile = Paper.book().read(Keys.MED_PROFILE_KEY);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiseaseToList();
                Navigation.findNavController(view).navigate(R.id.navigation_medication);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDiseaseToList();
                createMedicalProfile((MedicalProfile) Paper.book().read(Keys.MED_PROFILE_KEY));
            }
        });

        return view;
    }

    private void createMedicalProfile(final MedicalProfile profile) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return service.createMedicalProfile(profile);
            }

            @Override
            protected void onPostExecute(Boolean aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid) {
                    Paper.book().delete(Keys.MED_PROFILE_KEY);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        }.execute();
    }

    private void addDiseaseToList() {
        if(chkG6PD.isChecked()){
            profile.setG6pdDeficiency(true);
        }
        if(chkRespiratory.isChecked()){
            profile.setRespiratoryDiseases(true);
        }
        if(chkCardiovascular.isChecked()){
            profile.setCardiovascularDiseases(true);
        }
        if(chkDiabetes.isChecked()){
            profile.setDiabetes(true);
        }
        if(chkObesity.isChecked()){
            profile.setObesity(true);
        }
        Paper.book().write(Keys.MED_PROFILE_KEY, profile);
    }
}
