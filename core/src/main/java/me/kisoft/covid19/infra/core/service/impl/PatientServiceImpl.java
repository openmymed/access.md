/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.impl;

import java.util.List;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.enums.UserRole;
import me.kisoft.covid19.domain.core.repo.PatientRepository;
import me.kisoft.covid19.domain.core.service.PatientService;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Reccomendation;
import me.kisoft.covid19.domain.core.entity.Symptom;
import me.kisoft.covid19.infra.core.factory.PatientRepositoryFactory;

/**
 *
 * @author tareq
 */
public class PatientServiceImpl implements PatientService {

    @Override
    public void updatePatientMedicalProfile(Long patientId, MedicalProfile profile) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.findById(patientId);
            p.setProfile(profile);
            repo.save(p);
        }
    }

    @Override
    public void createPatient(User user) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = new Patient(user);
            repo.save(p);
        }
    }

    @Override
    public MedicalProfile getPatientMedicalProfile(Long id) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.findById(id);
            if (p != null) {
                return p.getProfile();
            }
        }
        return null;
    }

    @Override
    public List<Question> getPatientUnansweredQuestions(Long id) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getUnAnsweredPatientQuestions(id);
        }
    }

    @Override
    public List<Reccomendation> getPatientReccomendations(Long id) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientReccomendations(id);
        }
    }

    @Override
    public void answerPatientQuestion(Long id, Long questionId, String answer) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
             Patient p = repo.findById(id);
            if (p != null) {
               p.answerQuestion(answer, questionId);
               repo.save(p);
            }
        }
    }

    @Override
    public void addNewPatientSymptom(Long patientId, Symptom symptom) {
       try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
             Patient p = repo.findById(patientId);
            if (p != null) {
               p.addSymptom(symptom);
               repo.save(p);
            }
        }
    }
}
