package me.kisoft.covid19.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.QuestionType;
import me.kisoft.covid19.models.Symptom;

public class PatientServiceDelegate implements PatientService {

    PatientService service = new PatientServiceImpl();

    static List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Do you have fever?", "", QuestionType.Binary),
            new Question("Do you have any allergies?", "", QuestionType.Text),
            new Question("How are you?", "", QuestionType.Scale),
            new Question("How are you?", "", QuestionType.Scale)));
    //pre defined symptoms.
    static List<String> symptoms = new ArrayList<>(Arrays.asList("dry cough", "fever", "tiredness", "difficulty breathing"));

    @Override
    public Patient login(String username, String password) {
        return service.login(username, password);
    }

    @Override
    public Boolean register(Patient patient) {
        return service.register(patient);
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public List<String> getSymptoms() {
        return symptoms;
    }

    @Override
    public Boolean addSymptom(Symptom symptom) {
        return true;
    }

    @Override
    public Boolean answerQuestion(Question question) {
        return null;
    }


    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        return service.createMedicalProfile(profile);
    }
}
