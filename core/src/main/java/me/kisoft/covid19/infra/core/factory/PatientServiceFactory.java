/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.factory;

import me.kisoft.covid19.domain.core.service.PatientService;
import me.kisoft.covid19.infra.core.service.impl.PatientServiceImpl;
import me.kisoft.covid19.infra.factory.Factory;

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
