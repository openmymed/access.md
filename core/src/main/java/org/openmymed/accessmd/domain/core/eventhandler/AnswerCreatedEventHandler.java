/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.eventhandler;

import org.openmymed.accessmd.domain.core.entity.Answer;
import org.openmymed.accessmd.domain.core.enums.QuestionType;
import org.openmymed.accessmd.domain.core.service.DoctorService;
import org.openmymed.accessmd.domain.event.DomainEvent;
import org.openmymed.accessmd.domain.event.DomainEventHandler;
import org.openmymed.accessmd.domain.event.EventHandler;
import org.openmymed.accessmd.infra.core.factory.DoctorServiceFactory;
import org.openmymed.accessmd.infra.notification.delegate.NotificationServiceDelegate;

/**
 *
 * @author tareq
 */
@EventHandler
public class AnswerCreatedEventHandler extends DomainEventHandler {

    @Override
    public String getEventName() {
        return "answerCreated";
    }

    @Override
    public void doHandle(DomainEvent event) throws Exception {
        Answer a = event.getTargetAsClass(Answer.class);
        if (a.getQuestion().getType() == QuestionType.Vitals) {
            DoctorService service = DoctorServiceFactory.getInstance().get();
            service.archiveAnswer(a.getQuestion().getPatient().getDoctor().getId(),
                    a.getQuestion().getPatient().getId(), a.getId());
        } else {
            NotificationServiceDelegate.getInstance().addNotification(a.getQuestion().getPatient().getDoctor().getId(), getEventName(), a);
        }
    }

}
