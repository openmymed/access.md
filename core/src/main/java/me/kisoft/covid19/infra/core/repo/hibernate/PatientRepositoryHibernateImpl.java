/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.repo.hibernate;

import me.kisoft.covid19.domain.core.repo.PatientRepository;
import me.kisoft.covid19.domain.entity.Patient;
import me.kisoft.covid19.infra.repo.hiberante.HibernateCrudRepository;

/**
 *
 * @author tareq
 */
public class PatientRepositoryHibernateImpl extends HibernateCrudRepository<Patient> implements PatientRepository {

    @Override
    public Class<Patient> getType() {
        return Patient.class;
    }
    
}
