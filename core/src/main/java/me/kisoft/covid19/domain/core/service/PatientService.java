/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.service;

import java.util.List;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.core.entity.Doctor;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Reccomendation;
import me.kisoft.covid19.domain.core.entity.Symptom;

/**
 *
 * @author tareq
 */
public interface PatientService {

  void updatePatientMedicalProfile(Long patientId, MedicalProfile profile);

  void createPatient(User user);

  public MedicalProfile getPatientMedicalProfile(Long id);

  public List<Question> getPatientUnansweredQuestions(Long id);

  public List<Reccomendation> getPatientReccomendations(Long id);

  public void answerPatientQuestion(Long id, Long questionId, String answer);

  public void addNewPatientSymptom(Long patientId, Symptom symptom);

  public Doctor getPatientDoctor(Long patientId);

}
