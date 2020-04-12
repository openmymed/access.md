/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.rest;

import io.javalin.http.Context;
import me.kisoft.covid19.domain.auth.service.SecurityCodeService;
import me.kisoft.covid19.domain.core.service.DoctorService;
import me.kisoft.covid19.infra.auth.factory.SecurityCodeServiceFactory;
import me.kisoft.covid19.infra.core.factory.DoctorServiceFactory;

/**
 *
 * @author tareq
 */
public class DoctorRestService {
    
    private DoctorService doctorService = DoctorServiceFactory.getInstance().get();
    private SecurityCodeService securityCodeService = SecurityCodeServiceFactory.getInstance().get();
    
    public void getPatientsFeed(Context ctx){
    }
    
    public void getPatientProfile(Context ctx){
        
    }
    
    public void listPatientSymptoms(Context ctx){
        
    }
    
    public void listPatientAnswers(Context ctx){
        
        
    }
    
    public void listPatientQuestions(Context ctx){
        
    }
    
    public void createPatientQuestion(Context ctx){
        
    }
    
    public void deletePatientQuestion(Context ctx){
        
    }
    
    public void updatePatientQuestion(Context ctx){
        
    }
    
    public void getPatientQuestion(Context ctx){
        
        
        
    }
    
    public void createDoctor(Context ctx){
        
    }
    
}
