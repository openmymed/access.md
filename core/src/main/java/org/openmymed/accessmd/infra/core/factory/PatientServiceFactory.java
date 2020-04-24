/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.factory;

import org.openmymed.accessmd.domain.core.service.PatientService;
import org.openmymed.accessmd.infra.core.service.impl.PatientServiceImpl;
import org.openmymed.accessmd.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class PatientServiceFactory implements Factory<PatientService> {

    private static PatientServiceFactory instance = getInstance();

    private PatientServiceFactory() {
    }

    public static final PatientServiceFactory getInstance() {
        if (instance == null) {
            instance = new PatientServiceFactory();
        }
        return instance;
    }

    @Override
    public PatientService get() {
        return new PatientServiceImpl();
    }

}
