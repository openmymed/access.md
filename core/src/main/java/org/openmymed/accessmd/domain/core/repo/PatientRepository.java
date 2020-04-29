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
    
    /**
     * Get all questions asked to this patient
     * @param patientId the id of the patient to search for
     * @return  a list of questions
     */
     List<Question> getPatientQuestions(Long patientId);
      
     /**
      * Get all questions that have not been answered by the patient
      * @param patientId the id of the patient
      * @return a list of questions
      */
     List<Question> getUnAnsweredPatientQuestions(Long patientId);
     
     /**
      * Get all replies by the assigned doctor for this patient
      * @param patientId the id of the patient
      * @return a list of replies
      */
     List<Reply> getDoctorRepliesForPatient(Long patientId);
     
     /**
      * Finds a patient by and id and the doctor id assigned to it
      * @param patientId the id of the patient
      * @param doctorId the id of the doctor
      * @return  the patient, if found, null otherwise
      */
     Patient getPatientByDoctorAndId(Long patientId, Long doctorId);
     
}
