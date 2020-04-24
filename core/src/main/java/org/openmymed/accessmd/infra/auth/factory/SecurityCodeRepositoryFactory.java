/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.factory;
import org.openmymed.accessmd.infra.factory.Factory;
import org.openmymed.accessmd.domain.auth.repo.SecurityCodeRepository;
import org.openmymed.accessmd.infra.auth.repo.hibernate.SecurityCodeRepositoryHibernateImpl;
/**
 *
 * @author tareq
 */
public class SecurityCodeRepositoryFactory implements Factory<SecurityCodeRepository>{

    private static SecurityCodeRepositoryFactory instance = getInstance();
    private SecurityCodeRepositoryFactory(){
        
    }
    
    public static final SecurityCodeRepositoryFactory getInstance(){
        if(instance == null){
            instance = new SecurityCodeRepositoryFactory();
        }
        return instance;
    }
    
    
    @Override
    public SecurityCodeRepository get() {
        return new SecurityCodeRepositoryHibernateImpl();
    }
    
}
