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
    
    public List<Question> getAnsweredQuestionsSince(Long doctorId, Date date);
    
    public List<Symptom> getNewSymptomsSince(Long doctorId, Date date );
}
