package me.kisoft.covid19.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.kisoft.covid19.R;
import me.kisoft.covid19.adapters.QuestionsAdapter;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.services.PatientService;
import me.kisoft.covid19.services.PatientServiceMock;

public class HomeFragment extends Fragment {
    private RecyclerView rvHome;
    private QuestionsAdapter questionsAdapter;
    private List<Question> questions;
    private LinearLayoutManager linearLayoutManager;
    private ImageView imgDoctor;
    private TextView tvDoctorName;
    private Button btnChat;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //hide Action bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        //init screen components
        rvHome = root.findViewById(R.id.rv_home);
        imgDoctor = root.findViewById(R.id.img_doctor); //todo add doctor name and picture later..
        tvDoctorName = root.findViewById(R.id.tv_doctor_name);
        btnChat = root.findViewById(R.id.btn_chat);

        questions = new ArrayList<>();
        questionsAdapter = new QuestionsAdapter(questions);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvHome.setAdapter(questionsAdapter);
        rvHome.setLayoutManager(linearLayoutManager);
        getQuestions();

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo open chat screen
            }
        });

        return root;
    }

    void getQuestions() {
        new AsyncTask<Void, Void, Void>() {
            //todo check if this is correct.

            PatientService service = new PatientServiceMock();

            @Override
            protected Void doInBackground(Void... voids) {
                List<Question> questionList = service.getQuestions();
                for (Question q : questionList) {
                    questions.add(q);
                }
                questionsAdapter.notifyDataSetChanged();
                return null;
            }
        }.execute();
    }
}
