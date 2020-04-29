/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.impl;

import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.repo.PatientRepository;
import org.openmymed.accessmd.domain.core.service.PatientService;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Reply;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.entity.VitalsMeasurment;
import org.openmymed.accessmd.infra.core.factory.PatientRepositoryFactory;

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
    public List<Reply> getDoctorRepliesForPatient(Long id) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getDoctorRepliesForPatient(id);
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
    public void reportNewPatientSymptom(Long patientId, Symptom symptom) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.findById(patientId);
            if (p != null) {
                p.reportSymptom(symptom);
                repo.save(p);
            }
        }
    }

    @Override
    public Doctor getPatientAssignedDoctor(Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.findById(patientId).getDoctor();
        }
    }

    @Override
    public void reportNewVitalsMeasurment(Long patientId, VitalsMeasurment vitalsMeasurment) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.findById(patientId);
            if (p != null) {
                p.reportVitalsMesurment(vitalsMeasurment);
                repo.save(p);
            }
        }
    }
}
