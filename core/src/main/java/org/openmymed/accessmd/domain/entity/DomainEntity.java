/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.event.DomainEvent;
import org.openmymed.accessmd.domain.event.EventBus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@MappedSuperclass
@Audited
public abstract class DomainEntity {

    protected static transient final String DELETED_EVENT = "%sDeleted";
    protected static transient final String CREATED_EVENT = "%sCreated";
    protected static transient final String UPDATED_EVENT = "%sUpdated";
    @Transient
    private transient List<DomainEvent> eventQueue = new ArrayList<>();

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationDate;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDate;

    public void postDeleted() {
        this.queueEvent(String.format(DELETED_EVENT, getEntityName()), this);
        this.postAllEvents();
    }

    public void postCreated() {
        this.queueEvent(String.format(CREATED_EVENT, getEntityName()), this);
        this.postAllEvents();
    }

    public void postUpdated() {
        this.queueEvent(String.format(UPDATED_EVENT, getEntityName()), this);
        this.postAllEvents();
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

    /**
     * Queues events to be sent after the database transactions are complete and
     * the transaction succeeds
     *
     * @param name the name of the event
     * @param data the data to send
     */
    protected void queueEvent(String name, Object data) {
        DomainEvent event = new DomainEvent(name, this, data);
        this.eventQueue.add(event);
    }

    /**
     * Post all events to the message bus
     */
    private void postAllEvents() {
        for (DomainEvent event : eventQueue) {
            EventBus.getInstance().post(event);
        }
    }

    @Transient
    @JsonIgnore
    public abstract String getEntityName();
}
