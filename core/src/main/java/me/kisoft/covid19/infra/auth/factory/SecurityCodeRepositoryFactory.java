/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.factory;
import me.kisoft.covid19.infra.factory.Factory;
import me.kisoft.covid19.domain.auth.repo.SecurityCodeRepository;
import me.kisoft.covid19.infra.auth.repo.hibernate.SecurityCodeRepositoryHibernateImpl;
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
