/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.entity.VitalsMeasurment;
import org.openmymed.accessmd.domain.core.repo.DoctorRepository;
import org.openmymed.accessmd.domain.core.repo.PatientRepository;
import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.infra.core.factory.DoctorRepositoryFactory;
import org.openmymed.accessmd.infra.core.factory.PatientRepositoryFactory;

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
    public List<Symptom> getUnarchivedPatientSymptoms(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getUnarchivedSymptoms();
        }
    }

    @Override
    public List<Answer> getUnarchivedPatientAnswers(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getUnarchivedAnswers();
        }
    }

    @Override
    public void assignPatientToDoctor(Long doctorId, Long patientId) {
        try ( DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get();  PatientRepository patientRepo = PatientRepositoryFactory.getInstance().get();) {
            Patient patient = patientRepo.findById(patientId);
            Doctor doctor = doctorRepo.findById(doctorId);
            patient.setDoctor(doctor);
            doctor.assignPatient(patient);
            doctorRepo.save(doctor);
            patientRepo.save(patient);
        }
    }

    @Override
    public void createDoctor(User user) {
        try ( DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get()) {
            Doctor d = new Doctor(user);
            doctorRepo.save(d);
        }
    }

    @Override
    public void askPatientAQuestion(Long doctorId, Long patientId, Question question) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
            p.askQuestion(question);
            repo.save(p);
        }
    }

    @Override
    public List<Answer> getAllPatientsUnarchivedAnswers(Long doctorId) {
        return this.getDoctorPatients(doctorId).stream().flatMap(patient -> patient.getUnarchivedAnswers().stream()).collect(Collectors.toList());
    }

    @Override
    public List<Symptom> getAllPatientsUnarchivedSymptoms(Long doctorId) {
        return this.getDoctorPatients(doctorId).stream().flatMap(patient -> patient.getUnarchivedSymptoms().stream()).collect(Collectors.toList());

    }

    @Override
    public List<Question> getPatientQuestions(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId).getQuestions();
        }
    }

    @Override
    public void stopAskingPatientAQuestion(Long id, Long get, Long get0) {

    }

    @Override
    public void updatePatientQuestion(Long id, Long get, Long get0, Question bodyAsClass) {
    }

    @Override
    public Question getPatientQuestionDetails(Long id, Long get, Long get0) {
        return new Question();
    }

    @Override
    public List<Doctor> getDoctors() {
        try ( DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get()) {
            return doctorRepo.findAll();
        }
    }

    @Override
    public Patient getPatient(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            return repo.getPatientByDoctorAndId(patientId, doctorId);
        }

    }

    @Override
    public void archiveAnswer(Long doctorId, Long patientId, Long answerId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
            p.archiveAnswer(answerId);
            repo.save(p);
        }
    }

    @Override
    public void archiveSymptom(Long doctorId, Long patientId, Long symptomId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
            p.archiveSymptom(symptomId);
            repo.save(p);
        }
    }

    @Override
    public List<VitalsMeasurment> getPatientVitalsMeasurments(Long doctorId, Long patientId) {
        try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
            Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
            return p.getVitalsMeasurments();
        }
    }

}
