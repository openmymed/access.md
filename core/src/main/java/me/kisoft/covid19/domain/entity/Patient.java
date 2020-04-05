/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author tareq
 */
@Entity(name="Patient")
@Table(name="APP_USER")
public class Patient  extends DomainEntity{

    @Override
    public void postDeleted() {
    }

    @Override
    public void postSaved() {
    }

    @Override
    public void postUpdated() {
    }
    
}
