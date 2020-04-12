/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.repo;

import java.util.List;
import me.kisoft.covid19.domain.core.entity.Patient;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Reccomendation;
import me.kisoft.covid19.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface PatientRepository extends CrudRepository<Patient>{
    
     List<Question> getPatientQuestions(Long patientId);
      
     List<Question> getUnAnsweredPatientQuestions(Long patientId);
     
     List<Reccomendation> getPatientReccomendations(Long patientId);
     
     Patient getPatientByDoctorAndId(Long patientId, Long doctorId);
     
}
