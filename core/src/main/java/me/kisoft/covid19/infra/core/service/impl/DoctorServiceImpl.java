/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import me.kisoft.covid19.domain.auth.entity.SecurityCode;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.repo.SecurityCodeRepository;
import me.kisoft.covid19.domain.core.entity.Answer;
import me.kisoft.covid19.domain.core.entity.Doctor;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Symptom;
import me.kisoft.covid19.domain.core.repo.DoctorRepository;
import me.kisoft.covid19.domain.core.repo.PatientRepository;
import me.kisoft.covid19.domain.core.service.DoctorService;
import me.kisoft.covid19.infra.auth.factory.SecurityCodeRepositoryFactory;
import me.kisoft.covid19.infra.core.factory.DoctorRepositoryFactory;
import me.kisoft.covid19.infra.core.factory.PatientRepositoryFactory;

/**
 *
 * @author tareq
 */
public class DoctorServiceImpl implements DoctorService {

    @Override
    public List<Patient> getDoctorPatients(Long doctorId) {
        try ( DoctorRepository repo = DoctorRepositoryFactory.getInstance().get()) {
            return repo.findById(doctorId).getPatients();
        }

    }

    @Override
    public MedicalProfile getPatientProfile(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getProfile();
        }
    }

    @Override
    public List<Symptom> getPatientSymptoms(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getSymptoms();
        }
    }

    @Override
    public List<Answer> getPatientAnswers(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getQuestions()
                    .stream().flatMap(question -> question.getAnswers().stream()).collect(Collectors.toList());
        }
    }

    @Override
    public void addPatient(Long doctorId, Long patientId) {
        try (
                 DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get();  PatientRepository patientRepo = PatientRepositoryFactory.getInstance().get();) {

            Patient patient = patientRepo.findById(patientId);
            Doctor doctor = doctorRepo.findById(doctorId);
            patient.setDoctor(doctor);
            doctor.addPatient(patient);
            doctorRepo.save(doctor);
            patientRepo.save(patient);
        }
    }

    @Override
    public void createDoctor(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addQuestionForPatient(Long doctorId, Long patientId, Question question) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Answer> getAllPatientsNewAnswersSince(Long doctorId, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Symptom> getAllPatientsNewSymptomsSince(Long doctorId, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
