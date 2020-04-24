/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest;

import io.javalin.http.Context;
import org.openmymed.accessmd.domain.core.service.ICPCService;
import org.openmymed.accessmd.infra.core.factory.ICPCServiceFactory;

/**
 *
 * @author tareq
 */
public class ICPCRestService {
    private ICPCService service = ICPCServiceFactory.getInstance().get();
    
    public void getICPCSymptoms(Context ctx){
        ctx.json(service.getSymptoms());
        ctx.res.setStatus(200);
    }
}
