/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.event;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.java.Log;
import org.dizitart.jbus.JBus;
import org.reflections.Reflections;

/**
 *
 * @author tareq
 */
@Log
public class EventBus {

    private static EventBus instance = getInstance();

    private EventBus() {
        jbus = new JBus();
    }

    public static final EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }
    private JBus jbus;

    /**
     * Resets the event bus
     */
    public void removeHandlers() {
        jbus = new JBus();
    }

    /**
     * Posts an event to the domain bus
     * @param event  the event to post
     */
    public void post(DomainEvent event) {
        jbus.post(event);
    }

    /**
     * Add a handler to the event bus
     * @param handler  the handler to add
     */
    public void subscribe(DomainEventHandler handler) {
        jbus.register(handler);
    }
}
