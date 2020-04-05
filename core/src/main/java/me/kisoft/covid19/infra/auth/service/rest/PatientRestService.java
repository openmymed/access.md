/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.auth.service.rest;

import io.javalin.http.Context;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.entity.UserRole;
import me.kisoft.covid19.domain.auth.repo.UserRepository;
import me.kisoft.covid19.domain.auth.service.UserService;
import me.kisoft.covid19.infra.auth.factory.UserRepositoryFactory;
import me.kisoft.covid19.infra.auth.factory.UserServiceFactory;

/**
 *
 * @author tareq
 */
public class PatientRestService {
     UserService userService = UserServiceFactory.getInstance().get();
     
     public void signUp(Context ctx) throws Exception {
        try ( UserRepository repo = UserRepositoryFactory.getInstance().get()) {
            User toCreate = ctx.bodyAsClass(User.class);
            toCreate.setUserRole(UserRole.ROLE_PATIENT);
            if (repo.getUserByUsername(toCreate.getUsername()) == null) {
                userService.signUp(toCreate);
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
            }
        }
    }
}
