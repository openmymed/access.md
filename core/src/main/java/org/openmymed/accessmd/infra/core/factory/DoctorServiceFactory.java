/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.factory;

import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.infra.core.service.impl.DoctorServiceImpl;
import org.openmymed.accessmd.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class DoctorServiceFactory  implements Factory<DoctorService> {

    private static DoctorServiceFactory instance = getInstance();

    private DoctorServiceFactory() {
    }

    public static final DoctorServiceFactory getInstance() {
        if (instance == null) {
            instance = new DoctorServiceFactory();
        }
        return instance;
    }

    @Override
    public DoctorService get() {
        return new DoctorServiceImpl();
    }

}
