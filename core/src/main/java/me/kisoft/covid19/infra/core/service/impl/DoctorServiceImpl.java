/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.core.entity.Answer;
import me.kisoft.covid19.domain.core.entity.Doctor;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Symptom;
import me.kisoft.covid19.domain.core.repo.DoctorRepository;
import me.kisoft.covid19.domain.core.repo.PatientRepository;
import me.kisoft.covid19.domain.core.service.DoctorService;
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
  public List<Symptom> getUnseenPatientSymptoms(Long doctorId, Long patientId) {
    try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
      return repo.getPatientByDoctorAndId(patientId, doctorId).getUnseenSymptoms();
    }
  }

  @Override
  public List<Answer> getUnseenPatientAnswers(Long doctorId, Long patientId) {
    try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
      return repo.getPatientByDoctorAndId(patientId, doctorId).getUnseenAnswers();
    }
  }

  @Override
  public void addPatient(Long doctorId, Long patientId) {
    try ( DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get();  PatientRepository patientRepo = PatientRepositoryFactory.getInstance().get();) {
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
    try ( DoctorRepository doctorRepo = DoctorRepositoryFactory.getInstance().get()) {
      Doctor d = new Doctor(user);
      doctorRepo.save(d);
    }
  }

  @Override
  public void addQuestionForPatient(Long doctorId, Long patientId, Question question) {

  }

  @Override
  public List<Answer> getAllPatientsUnseenAnswers(Long doctorId, Date date) {
    return new ArrayList<>();
  }

  @Override
  public List<Symptom> getAllPatientsUnseenSymptoms(Long doctorId, Date date) {
    return new ArrayList<>();
  }

  @Override
  public List<Question> getPatientQuestions(Long id, Long get) {
    return new ArrayList<>();
  }

  @Override
  public void removePatientQuestion(Long id, Long get, Long get0) {

  }

  @Override
  public void updatePatientQuestion(Long id, Long get, Long get0, Question bodyAsClass) {
  }

  @Override
  public Question getPatientQuestion(Long id, Long get, Long get0) {
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
  public void markAnswerSeen(Long doctorId, Long patientId, Long answerId) {
    try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
      Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
      p.markAnswerSeen(answerId);
      repo.save(p);
    }
  }

  @Override
  public void markSymptomSeen(Long doctorId, Long patientId, Long symptomId) {
    try ( PatientRepository repo = PatientRepositoryFactory.getInstance().get()) {
      Patient p = repo.getPatientByDoctorAndId(patientId, doctorId);
      p.markSymptomSeen(symptomId);
      repo.save(p);
    }
  }

}
