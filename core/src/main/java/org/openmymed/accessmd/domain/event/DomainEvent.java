/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.event;

import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Getter
@Setter
public class DomainEvent {

  private String eventName;
  private DomainEntity target;
  private Object data;
    public DomainEvent(String eventName, DomainEntity target,Object data) {
        this.eventName = eventName;
        this.target = target;
        this.data = data;
    }
  
  public <T extends DomainEntity> T getTargetAsClass(Class<T> clazz){
      return clazz.cast(target);
  }

  public <T> T getDataAsClass(Class<T> clazz) {
    return clazz.cast(data);
  }

  public void post() {
    EventBus.getInstance().post(this);
  }
}
