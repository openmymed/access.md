package me.kisoft.covid19.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.QuestionType;

public class PatientServiceDelegate implements PatientService {

    PatientService service = new PatientServiceImpl();

    static ArrayList<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Do you have fever?", "", QuestionType.Binary),
            new Question("Do you have any allergies?", "", QuestionType.Text),
            new Question("How are you?", "", QuestionType.Scale),
            new Question("How are you?", "", QuestionType.Scale)));

    @Override
    public Patient login(String username, String password) {
        //do stuff here
        return service.login(username, password);
    }

    @Override
    public Boolean register(Patient patient) {
        //do stuff here
        return service.register(patient);
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        //todo needs implementation
        return false;
    }
}
