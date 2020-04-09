package me.kisoft.covid19.fragments;

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
import me.kisoft.covid19.R;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.utils.RememberMe;

public class MedicalFlagsFragment extends Fragment {
    private Button btnFinish;
    private Button btnPrev;

    private CheckBox chkG6PD;
    private CheckBox chkObesity;
    private CheckBox chkDiabetes;
    private CheckBox chkRespiratory;
    private CheckBox chkCardiovascular;
    private List<String> diseases;
    private CheckBox[] checkBoxes;
    private MedicalProfile profile;

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

        checkBoxes = new CheckBox[]{chkG6PD, chkCardiovascular, chkRespiratory, chkDiabetes, chkObesity};

        Paper.init(getContext());

        profile = Paper.book().read(RememberMe.medProfile);
        diseases = new ArrayList<>();

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
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });

        return view;
    }

    private void addDiseaseToList() {
        diseases.clear();
        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                diseases.add(checkBoxes[i].getText().toString());
            }
        }
        profile.setMedicalFlags(diseases);
        Paper.book().write(RememberMe.medProfile, profile);
    }
}