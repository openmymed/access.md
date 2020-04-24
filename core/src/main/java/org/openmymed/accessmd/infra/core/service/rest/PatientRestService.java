/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest;

import io.javalin.http.Context;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.repo.UserRepository;
import org.openmymed.accessmd.domain.auth.service.SecurityCodeService;
import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.service.PatientService;
import org.openmymed.accessmd.domain.core.entity.MedicalProfile;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.infra.auth.factory.SecurityCodeServiceFactory;
import org.openmymed.accessmd.infra.auth.factory.UserRepositoryFactory;
import org.openmymed.accessmd.infra.core.factory.PatientServiceFactory;

/**
 *
 * @author tareq
 */
public class PatientRestService {

    PatientService patientService = PatientServiceFactory.getInstance().get();
    SecurityCodeService securityCodeService = SecurityCodeServiceFactory.getInstance().get();
    public void signUp(Context ctx) throws Exception {
        try ( UserRepository repo = UserRepositoryFactory.getInstance().get()) {
            User toCreate = ctx.bodyAsClass(User.class);
            if (repo.getUserByUsername(toCreate.getUsername()) == null) {
                patientService.createPatient(toCreate);
                ctx.res.setStatus(200);
            } else {
                ctx.res.setStatus(400);
            }
        }
    }

    public void getDoctor(Context ctx){
       User user = ctx.sessionAttribute("user");
       ctx.json(patientService.getPatientDoctor(user.getId()));
    }
    public void updateMedicalProfile(Context ctx) {
        User user = ctx.sessionAttribute("user");
        patientService.updatePatientMedicalProfile(user.getId(), ctx.bodyAsClass(MedicalProfile.class));
        ctx.res.setStatus(200);
    }

    public void getMedicalProfile(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(patientService.getPatientMedicalProfile(user.getId()));
        ctx.res.setStatus(200);
    }

    public void getUnansweredQuestions(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(patientService.getPatientUnansweredQuestions(user.getId()));
        ctx.res.setStatus(200);
    }

    public void getReccomendations(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(patientService.getPatientReccomendations(user.getId()));
        ctx.res.setStatus(200);
    }
    
    public void answerQuestion(Context ctx){
          User user = ctx.sessionAttribute("user");
          Long questionId = ctx.pathParam("id", Long.class).get();
          Answer answer = ctx.bodyAsClass(Answer.class);
          patientService.answerPatientQuestion(user.getId(), questionId,answer.getAnswer());
          ctx.status(200);
    }
    
    public void addSymptom(Context ctx){
          User user = ctx.sessionAttribute("user");
          patientService.addNewPatientSymptom(user.getId(), ctx.bodyAsClass(Symptom.class));
          ctx.status(200);
    }
    
    public void getSecurityCode(Context ctx){
        User user = ctx.sessionAttribute("user");
        ctx.json(securityCodeService.createSecurityCode(user.getId()));
         ctx.status(200);
    }
}
