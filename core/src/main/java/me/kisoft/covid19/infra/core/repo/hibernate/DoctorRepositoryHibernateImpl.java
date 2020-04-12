/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.repo.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.kisoft.covid19.domain.core.entity.Doctor;
import me.kisoft.covid19.domain.core.entity.Question;
import me.kisoft.covid19.domain.core.entity.Symptom;
import me.kisoft.covid19.domain.core.repo.DoctorRepository;
import me.kisoft.covid19.infra.repo.hiberante.HibernateCrudRepository;

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
    public List<Question> getAnsweredQuestionsSince(Long doctorId, Date date) {
        return new ArrayList<>();
    }

    @Override
    public List<Symptom> getNewSymptomsSince(Long doctorId, Date date) {
        return new ArrayList<>();
    }
    
}
