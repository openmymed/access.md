package me.kisoft.covid19.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import me.kisoft.covid19.ChatActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.adapters.QuestionsAdapter;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.Symptom;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;
//Home has the questions from teh doctor

public class HomeFragment extends Fragment {
    private RecyclerView rvHome;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions;
    PatientService service;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imgDoctor;
    private TextView tvDoctorName;
    private TextView tvNoQuestions;
    private Button btnChat;
    private FloatingActionButton fabAddSymptoms;
    private List<String> symptoms;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //hide Action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //init screen components
        rvHome = root.findViewById(R.id.rv_home);
        imgDoctor = root.findViewById(R.id.img_doctor); //todo add doctor name and picture later..
        tvDoctorName = root.findViewById(R.id.tv_doctor_name);
        btnChat = root.findViewById(R.id.btn_chat);
        tvNoQuestions = root.findViewById(R.id.tv_no_questions);
        fabAddSymptoms = root.findViewById(R.id.fab_add_symptoms);
        //init service
        service = new PatientServiceDelegate();

        questions = new ArrayList<>();
        questionsAdapter = new QuestionsAdapter(questions);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHome.setAdapter(questionsAdapter);
        rvHome.setLayoutManager(linearLayoutManager);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSymptoms();
        getQuestions();

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
            }
        });
        fabAddSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddSymptomDialog(getContext());
            }
        });
    }

    private void showAddSymptomDialog(Context c) {
        //giving margin for spinner and etSymptom.
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(48, 16, 48, 0);
        //creating adapter for spinner to add data.
        ArrayAdapter<String> symptomsAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, symptoms);
        symptomsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //creating components of the dialog.
        final Spinner spSymptoms = new Spinner(c);
        spSymptoms.setLayoutParams(layoutParams);
        spSymptoms.setAdapter(symptomsAdapter);
        final EditText etSymptomNote = new EditText(c);
        etSymptomNote.setLines(3);
        etSymptomNote.setLayoutParams(layoutParams);
        etSymptomNote.setHint(R.string.notes);
        //creating linear layout to collect the components.
        final LinearLayout llSymptoms = new LinearLayout(c);
        llSymptoms.setOrientation(LinearLayout.VERTICAL);
        llSymptoms.addView(spSymptoms);
        llSymptoms.addView(etSymptomNote);

        AlertDialog dialog = new AlertDialog.Builder(c, R.style.CustomAlertDialog)
                .setTitle(getString(R.string.add_symptoms_title))
                .setView(llSymptoms)
                .setPositiveButton(getString(R.string.btn_submit), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String symptom = spSymptoms.getSelectedItem().toString();
                        String note = String.valueOf(etSymptomNote.getText());
                        addSymptoms(new Symptom(symptom, note));
                    }
                })
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .create();
        dialog.show();
    }

    void getQuestions() {
        new AsyncTask<Void, Void, List<Question>>() {

            @Override
            protected List<Question> doInBackground(Void... voids) {
                return service.getQuestions();
            }

            @Override
            protected void onPostExecute(List<Question> questionList) {
                if (questionList.isEmpty()) {
                    tvNoQuestions.setVisibility(View.VISIBLE);
                } else {
                    rvHome.setVisibility(View.VISIBLE);
                    for (Question q : questionList) {
                        questions.add(q);
                    }
                    questionsAdapter.notifyDataSetChanged();
                }
                super.onPostExecute(questions);
            }
        }.execute();
    }

    void getSymptoms() {
        new AsyncTask<Void, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... voids) {
                return service.getSymptoms();
            }

            @Override
            protected void onPostExecute(List<String> symptomList) {
                symptoms = symptomList;
                super.onPostExecute(symptomList);
            }
        }.execute();
    }

    void addSymptoms(final Symptom symptom) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return service.addSymptom(symptom);
            }
        }.execute();
    }
}
