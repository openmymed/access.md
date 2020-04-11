/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.service;

import java.util.List;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.core.entity.MedicalProfile;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.RecurringQuestion;

/**
 *
 * @author tareq
 */
public interface DoctorService {
    public List<Patient> getDoctorPatients(Long doctorid);
 
    public MedicalProfile getPatientProfile(Long doctorId, Long patientId);
    public void getPatientSymptoms(Long doctorId, Long patientId);
  
    public void getPatientAnswers(Long doctorId, Long patientId);
   
    public void addRecurringQuestion(Long doctorId, Long patientId, RecurringQuestion question);

    public void addOneTimeQuestion(Long patientId, Long doctorId, Question question);

    public void addPatient(Long doctorId, String patientSecurityCode);
    
    public void createDoctor(User user);
}
