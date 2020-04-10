/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.event.DomainEvent;
import me.kisoft.covid19.domain.event.EventBus;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@MappedSuperclass
@Audited
@Getter
@Setter
public abstract class DomainEntity {

    protected static transient final String DELETED_EVENT = "%sDeleted";
    protected static transient final String CREATED_EVENT = "%sCreated";
    protected static transient final String UPDATED_EVENT = "%sUpdated";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;


    public void postDeleted() {
        EventBus.getInstance().post(new DomainEvent(String.format(DELETED_EVENT, getEntityName()), this));
    }

    public void postCreated() {
        EventBus.getInstance().post(new DomainEvent(String.format(CREATED_EVENT, getEntityName()), this));
    }

    public void postUpdated() {
        EventBus.getInstance().post(new DomainEvent(String.format(UPDATED_EVENT, getEntityName()), this));
    }

    @PostPersist
    public void postPersist() {
        postCreated();
    }

    @PostUpdate
    public void postUpdate() {
        postUpdated();
    }

    @PostRemove
    public void postRemove() {
        postDeleted();
    }

    @Transient
    public abstract String getEntityName();
}
