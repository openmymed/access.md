/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.service;

import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Reply;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.entity.VitalsMeasurment;

/**
 *
 * @author tareq
 */
public interface PatientService {

  void updatePatientMedicalProfile(Long patientId, MedicalProfile profile);

  void createPatient(User user);

  public MedicalProfile getPatientMedicalProfile(Long id);

  public List<Question> getPatientUnansweredQuestions(Long id);

  public List<Reply> getPatientReccomendations(Long id);

  public void answerPatientQuestion(Long id, Long questionId, String answer);

  public void addNewPatientSymptom(Long patientId, Symptom symptom);

  public Doctor getPatientDoctor(Long patientId);

 public void addNewVitalsMeasurment(Long patientId, VitalsMeasurment vitalsMeasurment);

}
