/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.openmymed.accessmd.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity
@Getter
@Setter
public class VitalsMeasurment extends DomainEntity {

    @ManyToOne
    private Patient patient;
    private double heartBeatsPerMinute;
    private double systolicPressure;
    private double diastolicPressure;
    private double bloodOxygenation;
    private double breathingRate;

    @Override
    public String getEntityName() {
        return "vitalsMeasurment";
    }

}
