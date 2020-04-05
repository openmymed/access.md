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
import lombok.Data;
import org.hibernate.envers.Audited;

/**
 *
 * @author tareq
 */
@MappedSuperclass
@Audited
public abstract class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void postDeleted();

    public abstract void postSaved();

    public abstract void postUpdated();

    @PostPersist
    public void postPersist() {
        postSaved();
    }

    @PostUpdate
    public void postUpdate() {
        postUpdated();
    }

    @PostRemove
    public void postRemove() {
        postDeleted();
    }
}
