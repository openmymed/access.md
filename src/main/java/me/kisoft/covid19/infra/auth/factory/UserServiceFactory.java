/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.factory;

import me.kisoft.covid19.infra.factory.Factory;
import me.kisoft.covid19.domain.auth.service.UserService;
import me.kisoft.covid19.infra.auth.service.impl.UserServiceImpl;

/**
 *
 * @author tareq
 */
public class UserServiceFactory implements Factory<UserService>{
    private static UserServiceFactory instance = getInstance();
    private UserServiceFactory(){
        
    }
    
    public static final UserServiceFactory getInstance(){
        if(instance == null){
            instance = new UserServiceFactory();
        }
        return instance;
    }
    
    
    @Override
    public UserService get() {
        return new UserServiceImpl();
    }
}
