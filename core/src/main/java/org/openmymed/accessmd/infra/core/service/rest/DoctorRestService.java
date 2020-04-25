/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.infra.core.service.rest;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openmymed.accessmd.domain.auth.entity.User;
import org.openmymed.accessmd.domain.auth.service.SecurityCodeService;
import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.entity.Question;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.infra.auth.factory.SecurityCodeServiceFactory;
import org.openmymed.accessmd.infra.core.factory.DoctorServiceFactory;
import org.openmymed.accessmd.infra.core.service.rest.vo.PatientUpdateVo;

/**
 *
 * @author tareq
 */
public class DoctorRestService {

    private DoctorService doctorService = DoctorServiceFactory.getInstance().get();
    private SecurityCodeService securityCodeService = SecurityCodeServiceFactory.getInstance().get();

    public void getUnseenAnswerCount(Context ctx) {
        User user = ctx.sessionAttribute("user");
        HashMap map = new HashMap();
        map.put("count", doctorService.getAllPatientsUnseenAnswers(user.getId()).size());
        ctx.json(map);
    }

    public void getUnseenSymptomCount(Context ctx) {
        User user = ctx.sessionAttribute("user");
        HashMap map = new HashMap();
        map.put("count", doctorService.getAllPatientsUnseenSymptoms(user.getId()).size());
        ctx.json(map);
    }

    public void getPatientCount(Context ctx) {
        User user = ctx.sessionAttribute("user");
        HashMap map = new HashMap();
        map.put("count", doctorService.getDoctorPatients(user.getId()).size());
        ctx.json(map);
    }

    public void getPatientsFeed(Context ctx) {
        User user = ctx.sessionAttribute("user");
        List<Answer> answers = doctorService.getAllPatientsUnseenAnswers(user.getId());
        List<Symptom> symptoms = doctorService.getAllPatientsUnseenSymptoms(user.getId());
        List<PatientUpdateVo> updates = new ArrayList<>();
        answers.stream().forEach(answer -> {
            updates.add(new PatientUpdateVo(answer.getQuestion().getPatient(), answer));
        });
        symptoms.stream().forEach(symptom -> {
            updates.add(new PatientUpdateVo(symptom.getPatient(), symptom));
        });
        ctx.json(updates);
    }

    public void getPatientProfile(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientProfile(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void listPatientSymptoms(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getUnarchivedPatientSymptoms(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void listPatientAnswers(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getUnarchivedPatientAnswers(user.getId(), ctx.pathParam("id", Long.class).get()));
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
        doctorService.updatePatientQuestion(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("question_id", Long.class).get(), ctx.bodyAsClass(Question.class));
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

    public void getDoctors(Context ctx) {
        ctx.json(doctorService.getDoctors());
        ctx.status(200);
    }

    public void listPatients(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getDoctorPatients(user.getId()));
        ctx.status(200);
    }

    public void consumePatientCode(Context ctx) {
        User user = ctx.sessionAttribute("user");
        HashMap<String, String> map = ctx.bodyAsClass(HashMap.class);
        long consumeSecurityCode = securityCodeService.consumeSecurityCode(map.get("code"), user.getId());
        if (consumeSecurityCode > 0) {
            doctorService.addPatient(user.getId(), consumeSecurityCode);
        }
        ctx.status(200);
    }

    public void getPatient(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatient(user.getId(), ctx.pathParam("id", Long.class).get()));
    }

    public void markAnswerSeen(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.archiveAnswer(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("answer_id", Long.class).get());
        ctx.status(200);
    }

    public void markSymptomSeen(Context ctx) {
        User user = ctx.sessionAttribute("user");
        doctorService.archiveSymptom(user.getId(), ctx.pathParam("id", Long.class).get(), ctx.pathParam("symptom_id", Long.class).get());
        ctx.status(200);
    }

    public void getPatientVitalsMeasurments(Context ctx) {
        User user = ctx.sessionAttribute("user");
        ctx.json(doctorService.getPatientVitalsMeasurments(user.getId(), ctx.pathParam("id", Long.class).get()));
        ctx.status(200);
    }
}
