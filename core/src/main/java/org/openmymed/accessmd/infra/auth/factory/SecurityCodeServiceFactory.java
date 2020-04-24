/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.factory;

import org.openmymed.accessmd.infra.factory.Factory;
import org.openmymed.accessmd.domain.auth.service.SecurityCodeService;
import org.openmymed.accessmd.infra.auth.service.impl.SecurityCodeServiceImpl;

/**
 *
 * @author tareq
 */
public class SecurityCodeServiceFactory implements Factory<SecurityCodeService>{
    private static SecurityCodeServiceFactory instance = getInstance();
    private SecurityCodeServiceFactory(){
        
    }
    
    public static final SecurityCodeServiceFactory getInstance(){
        if(instance == null){
            instance = new SecurityCodeServiceFactory();
        }
        return instance;
    }
    
    
    @Override
    public SecurityCodeService get() {
        return new SecurityCodeServiceImpl();
    }
}
