/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class DomainEvent {

  private String eventName;
  private Object eventData;

    public DomainEvent(String eventName, Object eventData) {
        this.eventName = eventName;
        this.eventData = eventData;
    }
  
  

  public <T> T getAsClass(Class<T> clazz) {
    return clazz.cast(eventData);
  }

  public void post() {
    EventBus.getInstance().post(this);
  }
}
