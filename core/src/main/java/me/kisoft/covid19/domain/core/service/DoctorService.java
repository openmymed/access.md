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
 
    public MedicalProfile getPatientProfile(Long doctorId, Long patientId);
    
    
    public List<Symptom> getPatientSymptoms(Long doctorId, Long patientId);
  
    public List<Answer> getPatientAnswers(Long doctorId, Long patientId);
   
    public List<Answer> getAllPatientsNewAnswersSince(Long doctorId, Date date);
    
    public List<Symptom> getAllPatientsNewSymptomsSince(Long doctorId, Date date);

    public void addQuestionForPatient(Long doctorId,Long patientId,  Question question);

    public void addPatient(Long doctorId, Long patientId);
    
    public void createDoctor(User user);

    public List<Question> getPatientQuestions(Long id, Long patientId);

    public void removePatientQuestion(Long id, Long patientId, Long questionId);

    public void updatePatientQuestion(Long id, Long patientId, Long questionId, Question question);

    public Object getPatientQuestion(Long id, Long patientId, Long questionId);

}
