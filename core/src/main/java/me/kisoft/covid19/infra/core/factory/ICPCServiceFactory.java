/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.factory;

import me.kisoft.covid19.domain.core.service.ICPCService;
import me.kisoft.covid19.infra.core.service.impl.ICPCServiceImpl;
import me.kisoft.covid19.infra.factory.Factory;

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
        return this.service;
    }
    
}
