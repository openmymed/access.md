package me.kisoft.covid19.services;

import java.util.List;

import me.kisoft.covid19.Exceptions.UnauthorizedException;
import me.kisoft.covid19.models.Answer;
import me.kisoft.covid19.models.Doctor;
import me.kisoft.covid19.models.ICPCEntry;
import me.kisoft.covid19.models.MedicalProfile;
import me.kisoft.covid19.models.Patient;
import me.kisoft.covid19.models.Question;
import me.kisoft.covid19.models.SecurityCode;
import me.kisoft.covid19.models.Symptom;

public interface PatientService {

    Patient login(String username, String password);

    Boolean register(Patient patient);

    List<Question> getQuestions();

    List<ICPCEntry> getICPC();

    Boolean addSymptom(Symptom symptom);

    Boolean answerQuestion(Answer answer, Long questionId);

    Boolean createMedicalProfile(MedicalProfile profile);

    SecurityCode getSecurityCode();

    Doctor getDoctor();
}
