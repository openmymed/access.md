/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.covid19.domain.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import me.kisoft.covid19.domain.entity.DomainEntity;

/**
 *
 * @author tareq
 */
@Entity(name="Answer")
@Table(name="ANSWERS")
@Getter
@Setter
public class Answer extends DomainEntity {
    @ManyToOne
    private Question question;
    private String answer;

    @Override
    public String getEntityName() {
        return "answer";
    }
    
    
}
