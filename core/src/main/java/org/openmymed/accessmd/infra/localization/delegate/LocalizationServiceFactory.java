/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.localization.delegate;

import java.util.Locale;
import org.openmymed.accessmd.domain.localization.service.LocalizationService;
import org.openmymed.accessmd.infra.factory.Factory;
import org.openmymed.accessmd.infra.localization.service.LocalizationServiceImpl;

/**
 *
 * @author tareq
 */
public class LocalizationServiceFactory implements Factory<LocalizationService> {

    private static LocalizationServiceFactory instance = getInstance();
    private LocalizationService service;

    private LocalizationServiceFactory() {
        service = new LocalizationServiceImpl();
    }

    public static final LocalizationServiceFactory getInstance() {
        if (instance == null) {
            instance = new LocalizationServiceFactory();
        }
        return instance;
    }

    @Override
    public LocalizationService get() {
        return service;
    }

}
