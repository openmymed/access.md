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

  public void searchForHandlers() {
    Reflections r = new Reflections(DomainEventHandler.class);
    for (Class clazz : r.getTypesAnnotatedWith(EventHandler.class)) {
      try {
        Object o = clazz.getConstructor().newInstance();
        if (o instanceof DomainEventHandler) {
          EventBus.getInstance().subscribe((DomainEventHandler) o);
          log.info("Added Event Handler " + clazz.getSimpleName());
        }
      } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void removeHandlers() {
    jbus = new JBus();
  }

  public void post(DomainEvent event) {
    jbus.post(event);
  }

  public void subscribe(DomainEventHandler handler) {
    jbus.register(handler);
  }
}
