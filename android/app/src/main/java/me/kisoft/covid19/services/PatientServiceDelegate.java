package me.kisoft.covid19.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.QuestionType;
import me.kisoft.covid19.models.UserRole;

public class PatientServiceDelegate implements PatientService {

    static ArrayList<Patient> patients = new ArrayList<>(Arrays.asList(
            new Patient("Admin", "Admin", UserRole.ROLE_PATIENT, "0591234561"),
            new Patient("0591234561", "Admin", UserRole.ROLE_PATIENT, "0591234561"),
            new Patient("0592234561", "Admin", UserRole.ROLE_PATIENT, "0591234561"),
            new Patient("0593234561", "Admin", UserRole.ROLE_PATIENT, "0591234561"),
            new Patient("0594234561", "Admin", UserRole.ROLE_PATIENT, "0591234561")));
    PatientService service = new PatientServiceImpl();

    static ArrayList<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Do you have fever?", "", QuestionType.Binary),
            new Question("Do you have any allergies?", "", QuestionType.Text),
            new Question("How are you?", "", QuestionType.Scale),
            new Question("How are you?", "", QuestionType.Scale)));

    @Override
    public Patient login(String username, String password) {
        //do stuff here
        for (Patient patient : patients) {
            if (patient.getUsername().equals(username) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public Boolean register(Patient patient) {
        //do stuff here
        patients.add(patient);
        return true;
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }
}
