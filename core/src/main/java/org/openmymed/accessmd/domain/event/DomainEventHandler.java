/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.event;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.dizitart.jbus.Subscribe;
import org.openmymed.accessmd.app.App;
import org.reflections.Reflections;

/**
 *
 * @author tareq
 */
@Log
public abstract class DomainEventHandler {

    public abstract String getEventName();

    public abstract void doHandle(DomainEvent event) throws Exception;

    public static final void subscribeHandlers() {
        Reflections r = new Reflections("org.openmymed");
        for (Class clazz : r.getTypesAnnotatedWith(EventHandler.class)) {
            try {
                Object o = clazz.getConstructor().newInstance();
                if (o instanceof DomainEventHandler) {
                    EventBus.getInstance().subscribe((DomainEventHandler) o);
                    log.log(Level.INFO, "Added Event Handler {0}", clazz.getSimpleName());
                }
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Subscribe(async = true)
    public final void handleEvent(DomainEvent event) throws Throwable {
        log.log(Level.FINEST, "Event Thrown : {0}", event.getEventName());
        try {
            if (StringUtils.equals(getEventName(), event.getEventName())) {
                doHandle(event);
            }
        } catch (Exception ex) {
            log.severe(ex.getMessage());
            throw ex;
        }
    }

}
