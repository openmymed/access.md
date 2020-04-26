/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.eventhandler;

import lombok.extern.java.Log;
import org.openmymed.accessmd.domain.core.entity.Symptom;
import org.openmymed.accessmd.domain.event.DomainEvent;
import org.openmymed.accessmd.domain.event.DomainEventHandler;
import org.openmymed.accessmd.domain.event.EventHandler;
import org.openmymed.accessmd.infra.notification.delegate.NotificationServiceDelegate;

/**
 *
 * @author tareq
 */
@Log
@EventHandler
public class SymptomCreatedEventHandler extends DomainEventHandler {

    @Override
    public String getEventName() {
        return "symptomCreated";
    }

    @Override
    public void doHandle(DomainEvent event) throws Exception {
        NotificationServiceDelegate.getInstance().addNotification(event.getTargetAsClass(Symptom.class).getPatient().getDoctor().getId(), getEventName(), event.getTarget());
    }


}
