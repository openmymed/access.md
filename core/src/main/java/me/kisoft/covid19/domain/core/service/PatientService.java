/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.service;

import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.entity.MedicalProfile;

/**
 *
 * @author tareq
 */
public interface PatientService {
    
    void updatePatientMedicalProfile(Long patientId, MedicalProfile  profile);
    void createPatient(User user);

    public MedicalProfile getPatientMedicalProfile(Long id);
    
    
}
