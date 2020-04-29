/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.repo.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openmymed.accessmd.domain.core.entity.Doctor;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.repo.DoctorRepository;
import org.openmymed.accessmd.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class DoctorRepositoryHibernateImpl extends HibernateCrudRepository<Doctor> implements DoctorRepository {

    @Override
    public Class<Doctor> getType() {
        return Doctor.class;
    }

    @Override
    public List<Question> getNewAnswersSince(Long doctorId, Date date) {
        return new ArrayList<>();
    }

    @Override
    public List<Symptom> getNewSymptomsSince(Long doctorId, Date date) {
        return new ArrayList<>();
    }
    
}
