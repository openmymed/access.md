/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.repo;

import java.util.List;
import org.openmymed.accessmd.domain.core.entity.Patient;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Reply;
import org.openmymed.accessmd.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface PatientRepository extends CrudRepository<Patient>{
    
     List<Question> getPatientQuestions(Long patientId);
      
     List<Question> getUnAnsweredPatientQuestions(Long patientId);
     
     List<Reply> getPatientReccomendations(Long patientId);
     
     Patient getPatientByDoctorAndId(Long patientId, Long doctorId);
     
}
