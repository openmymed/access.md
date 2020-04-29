/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.repo;

import java.util.Date;
import java.util.List;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.repo.CrudRepository;

/**
 *
 * @author tareq
 */
public interface DoctorRepository extends CrudRepository<Doctor>{
    
    /**
     * Get new answers since this date
     * @param doctorId the doctor id
     * @param date the date to check from
     * @return  a list of questions
     */
    public List<Question> getNewAnswersSince(Long doctorId, Date date);
    
    /**
     * Get new symptoms since this date
     * @param doctorId the doctor id
     * @param date the date to check from
     * @return  a list of answers
     */
    public List<Symptom> getNewSymptomsSince(Long doctorId, Date date );
}
