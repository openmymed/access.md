/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmymed.accessmd.domain.core.entity;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tareq
 */
@Embeddable
@Getter
@Setter
public class RecurringQuestionAskingTimes {
    private long hourOfDay;
    private long minuteOfHour;
    
}
