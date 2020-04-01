/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.event;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tareq
 */
@Getter
@Setter
@AllArgsConstructor
public class DomainEvent {

  private String eventName;
  private Object eventData;

  public <T> T getAsClass(Class<T> clazz) {
    return clazz.cast(eventData);
  }

  public void post() {
    EventBus.getInstance().post(this);
  }
}
