/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.factory;

import org.openmymed.accessmd.domain.core.repo.DoctorRepository;
import org.openmymed.accessmd.infra.core.repo.hibernate.DoctorRepositoryHibernateImpl;
import org.openmymed.accessmd.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class DoctorRepositoryFactory implements Factory<DoctorRepository> {

    private static DoctorRepositoryFactory instance = getInstance();

    private DoctorRepositoryFactory() {

    }

    public static final DoctorRepositoryFactory getInstance() {
        if (instance == null) {
            instance = new DoctorRepositoryFactory();
        }
        return instance;
    }

    @Override
    public DoctorRepository get() {
        return new DoctorRepositoryHibernateImpl();
    }

}