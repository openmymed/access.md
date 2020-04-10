/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.rest;

import io.javalin.http.Context;
import me.kisoft.covid19.domain.core.service.ICPCService;
import me.kisoft.covid19.infra.core.factory.ICPCServiceFactory;

/**
 *
 * @author tareq
 */
public class ICPCRestService {
    private ICPCService service = ICPCServiceFactory.getInstance().get();
    
    public void getICPCEntries(Context ctx){
        ctx.json(service.getEntries());
        ctx.res.setStatus(200);
    }
}
