/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.service;

import java.util.Date;
import java.util.List;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.core.entity.Answer;
import me.kisoft.covid19.domain.core.entity.Doctor;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Symptom;

/**
 *
 * @author tareq
 */
public interface DoctorService {
    public List<Patient> getDoctorPatients(Long doctorid);
 
    public List<Doctor> getDoctors();
    
    public MedicalProfile getPatientProfile(Long doctorId, Long patientId);
    
    
    public List<Symptom> getUnseenPatientSymptoms(Long doctorId, Long patientId);
  
    public List<Answer> getUnseenPatientAnswers(Long doctorId, Long patientId);
   
    public List<Answer> getAllPatientsUnseenAnswers(Long doctorId, Date date);
    
    public List<Symptom> getAllPatientsUnseenSymptoms(Long doctorId, Date date);

    public void addQuestionForPatient(Long doctorId,Long patientId,  Question question);

    public void addPatient(Long doctorId, Long patientId);
    
    public void createDoctor(User user);

    public List<Question> getPatientQuestions(Long id, Long patientId);

    public void removePatientQuestion(Long id, Long patientId, Long questionId);

    public void updatePatientQuestion(Long id, Long patientId, Long questionId, Question question);

    public Question getPatientQuestion(Long id, Long patientId, Long questionId);

  public Patient getPatient(Long id, Long get);

  public void markAnswerSeen(Long doctorId, Long patientId, Long answerId);

  public void markSymptomSeen(Long doctorId, Long patientId, Long answerId);

}
