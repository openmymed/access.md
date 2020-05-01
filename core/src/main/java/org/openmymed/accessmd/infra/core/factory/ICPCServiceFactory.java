/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.factory;

import java.util.List;
import java.util.Locale;
import org.openmymed.accessmd.domain.core.entity.ICPCEntry;
import org.openmymed.accessmd.domain.core.service.ICPCService;
import org.openmymed.accessmd.infra.core.service.impl.ICPCServiceImpl;
import org.openmymed.accessmd.infra.factory.Factory;

/**
 *
 * @author tareq
 */
public class ICPCServiceFactory implements Factory<ICPCService>{

    private static ICPCServiceFactory instance = getInstance();
    private final ICPCService service;
    private ICPCServiceFactory(){
        service = new ICPCServiceImpl();
    }
    public static final ICPCServiceFactory getInstance(){
        if(instance == null){
            instance = new ICPCServiceFactory();
        }
        return instance;
    }
    @Override
    public ICPCService get() {
        return service;
    }
    
  
}
