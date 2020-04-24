/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.factory;
import org.openmymed.accessmd.infra.factory.Factory;
import org.openmymed.accessmd.domain.auth.repo.UserRepository;
import org.openmymed.accessmd.infra.auth.repo.hibernate.UserRepositoryHibernateImpl;
/**
 *
 * @author tareq
 */
public class UserRepositoryFactory implements Factory<UserRepository>{

    private static UserRepositoryFactory instance = getInstance();
    private UserRepositoryFactory(){
        
    }
    
    public static final UserRepositoryFactory getInstance(){
        if(instance == null){
            instance = new UserRepositoryFactory();
        }
        return instance;
    }
    
    
    @Override
    public UserRepository get() {
        return new UserRepositoryHibernateImpl();
    }
    
}
