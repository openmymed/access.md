/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.factory;

import org.openmymed.accessmd.domain.core.repo.PatientRepository;
import org.openmymed.accessmd.infra.core.repo.hibernate.PatientRepositoryHibernateImpl;
import org.openmymed.accessmd.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class PatientRepositoryFactory implements Factory<PatientRepository> {

    private static PatientRepositoryFactory instance = getInstance();

    private PatientRepositoryFactory() {

    }

    public static final PatientRepositoryFactory getInstance() {
        if (instance == null) {
            instance = new PatientRepositoryFactory();
        }
        return instance;
    }

    @Override
    public PatientRepository get() {
        return new PatientRepositoryHibernateImpl();
    }

}
