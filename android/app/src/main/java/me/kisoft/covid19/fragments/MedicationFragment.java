package me.kisoft.covid19.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import me.kisoft.covid19.R;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.utils.RememberMe;

public class MedicationFragment extends Fragment {

    private Button btnNextStep;
    private Button btnPrevStep;
    private FloatingActionButton fabAddToList;
    private ListView medicationListView;
    private List<String> medications;
    private ArrayAdapter<String> medicationAdapter;
    private MedicalProfile profile = null;

    public MedicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication, container, false);
        btnNextStep = view.findViewById(R.id.btn_next_step);
        btnPrevStep = view.findViewById(R.id.btn_prev_step);
        fabAddToList = view.findViewById(R.id.fab_add_medication);
        medicationListView = view.findViewById(R.id.medication_listview);
        Paper.init(getContext());

        profile = Paper.book().read(RememberMe.medProfile);
        if (profile != null) {
            Log.e("", Paper.book().read(RememberMe.medProfile).toString());
            if (profile.getMedications() != null) {
                Log.e("hi", "");
                medications = profile.getMedications();
            } else {
                medications = new ArrayList<>();
            }
        }
        medicationAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, medications);
        medicationListView.setAdapter(medicationAdapter);

        fabAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog(getContext());
            }
        });

        btnPrevStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedToList(view, R.id.navigation_general_info);
            }
        });

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedToList(view, R.id.navigation_medical_flags);
//                Log.e("", Paper.book().read(RememberMe.medProfile).toString());
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void addMedToList(View view, int destination) {
        profile.setMedications(medications);
        Paper.book().write(RememberMe.medProfile, profile);
        Navigation.findNavController(view).navigate(destination);
    }

    private void showAddItemDialog(Context c) {
        final EditText etMedication = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle(getString(R.string.add_new_medication))
                .setView(etMedication)
                .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String medication = String.valueOf(etMedication.getText());
                        medications.add(medication);
                        medicationAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .create();
        dialog.show();
    }

}
