package me.kisoft.covid19.services;


import java.util.List;

import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.Symptom;

public class PatientServiceDelegate implements PatientService {

    PatientService service = new PatientServiceImpl();

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
        return service.getQuestions();
    }

    @Override
    public List<ICPCEntry> getICPC() {
        return service.getICPC();
    }

    @Override
    public Boolean addSymptom(Symptom symptom) {
        return service.addSymptom(symptom);
    }

    @Override
    public Boolean answerQuestion(Question question) {
        return service.answerQuestion(question);
    }


    @Override
    public Boolean createMedicalProfile(MedicalProfile profile) {
        return service.createMedicalProfile(profile);
    }

    @Override
    public String getSecurityCode() {
        return service.getSecurityCode();
    }
}
