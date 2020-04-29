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

    public DomainEvent(String eventName, DomainEntity target, Object data) {
        this.eventName = eventName;
        this.target = target;
        this.data = data;
    }

    /**
     * Gets the event target as a class
     *
     * @param <T> the class to get the target as
     * @param clazz the class to get the target as
     * @return the target as this class
     */
    public <T extends DomainEntity> T getTargetAsClass(Class<T> clazz) {
        return clazz.cast(target);
    }

    /**
     * Gets the event data as a class
     *
     * @param <T> the class to get the data as
     * @param clazz the class to get the data as
     * @return the target as this class
     */
    public <T> T getDataAsClass(Class<T> clazz) {
        return clazz.cast(data);
    }

    /**
     * Posts this event to the event bus
     */
    public void post() {
        EventBus.getInstance().post(this);
    }
}
