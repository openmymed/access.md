/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.Date;
import me.kisoft.covid19.domain.auth.entity.User;
import me.kisoft.covid19.domain.auth.service.SecurityCodeService;
import me.kisoft.covid19.domain.core.entity.Question;
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

    public void getPatientsFeed(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.getAllPatientsNewAnswersSince(user.getId(), new Date());
    }

    public void getPatientProfile(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientProfile(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void listPatientSymptoms(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientSymptoms(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void listPatientAnswers(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientAnswers(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void listPatientQuestions(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientQuestions(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void createPatientQuestion(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.addQuestionForPatient(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.bodyAsClass(Question.class));
        ctx.status(200);
    }

    public void deletePatientQuestion(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.removePatientQuestion(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("question_id", Long.class).get());
        ctx.status(200);
    }

    public void updatePatientQuestion(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.updatePatientQuestion(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("question_id", Long.class).get(),ctx.bodyAsClass(Question.class));
        ctx.status(200);
    }

    public void getPatientQuestion(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientQuestion(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("question_id", Long.class).get()));
    }

    public void createDoctor(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        doctorService.createDoctor(user);
        ctx.status(200);
    }
    
    public void getDoctors(Context ctx){
        ctx.json(doctorService.getDoctors());
        ctx.status(200);
    }

}
