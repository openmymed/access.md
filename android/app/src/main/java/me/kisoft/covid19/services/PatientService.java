package me.kisoft.covid19.services;

import java.util.List;

import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;

public interface PatientService {
    Patient login(String username, String password);

    Boolean register(Patient patient);

    List<Question> getQuestions();
}
