/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.event;

import java.util.logging.Level;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.dizitart.jbus.Subscribe;

/**
 *
 * @author tareq
 */
@Log
public abstract class DomainEventHandler<T> {

  @Subscribe(async = true)
  public void handleEvent(DomainEvent event) throws Throwable {
    log.log(Level.FINEST, "Event Thrown : {0}", event.getEventName());
    try {
      if (StringUtils.equals(getEventName(), event.getEventName())) {
        if (event.getEventData() != null) {
          doHandle(event.getAsClass(getType()));
        } else {
          doHandle(null);
        }
      }
    } catch (Throwable ex) {
      log.severe(ex.getMessage());
      throw ex;
    }
  }

  public abstract String getEventName();

  public abstract void doHandle(T eventData) throws Exception;

  public abstract Class<T> getType();

}
