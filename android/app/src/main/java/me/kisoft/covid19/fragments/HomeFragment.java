package me.kisoft.covid19.fragments;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;
import me.kisoft.covid19.ChatActivity;
import me.kisoft.covid19.R;
import me.kisoft.covid19.adapters.QuestionsAdapter;
import me.kisoft.covid19.models.Doctor;
import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.models.Notification;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.Symptom;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceDelegate;
import me.kisoft.covid19.utils.Keys;
import me.kisoft.covid19.utils.NotificationUtility;

//Home has the questions from the doctor
public class HomeFragment extends Fragment {
    private RecyclerView rvHome;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions;
    private PatientService service;
    private SwipeRefreshLayout pullToRefresh;
    private LinearLayoutManager linearLayoutManager;
    private TextView tvDoctorName;
    private TextView tvNoQuestions;
    private Button btnChat;
    private FloatingActionButton fabAddSymptoms;
    @Override
    public void onStart() {
        super.onStart();
        getDoctor();
        getQuestions();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //hide Action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //init screen components
        rvHome = root.findViewById(R.id.rv_home);
        service = new PatientServiceDelegate();
        tvDoctorName = root.findViewById(R.id.tv_doctor_name);
        btnChat = root.findViewById(R.id.btn_chat);
        tvNoQuestions = root.findViewById(R.id.tv_no_questions);
        fabAddSymptoms = root.findViewById(R.id.fab_add_symptoms);
        pullToRefresh = root.findViewById(R.id.pullToRefresh);
        pullToRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        //init service


        questions = new ArrayList<>();
        questionsAdapter = new QuestionsAdapter(questions, getContext());
        questionsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (questions.isEmpty()) {
                    rvHome.setVisibility(View.GONE);
                    tvNoQuestions.setVisibility(View.VISIBLE);
                } else {
                    tvNoQuestions.setVisibility(View.GONE);
                    rvHome.setVisibility(View.VISIBLE);
                }
            }
        });

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHome.setAdapter(questionsAdapter);
        rvHome.setLayoutManager(linearLayoutManager);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
        //        getQuestions();
                Log.e("refresh","on refresh");
            }
        });
    }

    private void showAddSymptomDialog(Context c) {
        //giving margin for spinner and etSymptom.
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(48, 16, 48, 0);
        //creating adapter for spinner to add data.
        ArrayAdapter<ICPCEntry> aICPC = new ArrayAdapter<ICPCEntry>(getContext(), android.R.layout.simple_spinner_item, (List<ICPCEntry>) Paper.book().read(Keys.ICPC_KEY));
        aICPC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //creating components of the dialog.
        final SearchableSpinner spSymptoms = new SearchableSpinner(c);
        spSymptoms.setLayoutParams(layoutParams);
        spSymptoms.setTitle(getString(R.string.select_symptom));
        spSymptoms.setPositiveButton(getString(R.string.btn_search));
        spSymptoms.setAdapter(aICPC);
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
                        ICPCEntry icpcEntry = (ICPCEntry) spSymptoms.getSelectedItem();
                        String note = String.valueOf(etSymptomNote.getText());
                        addSymptoms(new Symptom(icpcEntry.getCode(), note));
                    }
                })
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .create();
        dialog.show();
    }

    private void getQuestions() {
        Log.e("Calling", "Get Question every 5 mins");
        new AsyncTask<Void, Void, List<Question>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
              pullToRefresh.setRefreshing(true);
            }

            @Override
            protected List<Question> doInBackground(Void... voids) {
                return service.getQuestions();
            }

            @Override
            protected void onPostExecute(List<Question> questionList) {
                pullToRefresh.setRefreshing(false);
                questions.clear();
                questions.addAll(questionList);
                questionsAdapter.notifyDataSetChanged();

                super.onPostExecute(questionList);
            }
        }.execute();
    }



    private void addSymptoms(final Symptom symptom) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                return service.addSymptom(symptom);
            }
        }.execute();
    }

    private void getDoctor() {
        new AsyncTask<Void, Void, Doctor>() {

            @Override
            protected Doctor doInBackground(Void... voids) {
                return service.getDoctor();
            }

            @Override
            protected void onPostExecute(Doctor doctor) {
                super.onPostExecute(doctor);
                if (doctor != null) {
                    String doctorName = "Dr. " + doctor.getFirstName() + " " + doctor.getLastName();
                    tvDoctorName.setText(doctorName);
                }
            }
        }.execute();
    }


}
