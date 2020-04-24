/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.auth.service.rest;

import io.javalin.http.Context;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.repo.UserRepository;
import org.openmymed.accessmd.domain.auth.service.UserService;
import org.openmymed.accessmd.infra.auth.factory.UserRepositoryFactory;
import org.openmymed.accessmd.infra.auth.factory.UserServiceFactory;
import org.openmymed.accessmd.infra.auth.service.rest.vo.SignInRequest;

/**
 *
 * @author tareq
 */
public class UserRestService {
    
    UserService userService = UserServiceFactory.getInstance().get();
    
    public void signIn(Context ctx) throws Exception {
        SignInRequest request = ctx.bodyAsClass(SignInRequest.class);
        User foundUser = userService.signIn(request.getUsername(), request.getPassword());
        if (foundUser != null) {
            ctx.req.getSession(true);
            ctx.sessionAttribute("authenticated", true);
            ctx.sessionAttribute("user", foundUser);
            ctx.json(foundUser);
        } else {
            ctx.res.setStatus(403);
        }
    }
    
    
    
}
