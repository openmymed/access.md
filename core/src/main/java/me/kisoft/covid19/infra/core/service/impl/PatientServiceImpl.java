/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.impl;

import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.entity.UserRole;
import me.kisoft.covid19.domain.core.repo.PatientRepository;
import me.kisoft.covid19.domain.core.service.PatientService;
import me.kisoft.covid19.domain.entity.MedicalProfile;
import me.kisoft.covid19.domain.entity.Patient;
import me.kisoft.covid19.infra.core.factory.PatientRepositoryFactory;

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
            if (p.getId() != null) {
              return p.getProfile();
            }
        }
        return null;
    }

}
